package com.kameleoon.bett.vote.service;

import com.kameleoon.bett.exeption.ValidationException;
import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.quote.service.QuoteService;
import com.kameleoon.bett.user.model.User;
import com.kameleoon.bett.user.service.UserService;
import com.kameleoon.bett.vote.dao.VoteRepository;
import com.kameleoon.bett.vote.dto.VoteDto;
import com.kameleoon.bett.vote.mapper.VoteMapper;
import com.kameleoon.bett.vote.model.Reaction;
import com.kameleoon.bett.vote.model.Vote;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final UserService userService;
    private final QuoteService quoteService;

    public VoteServiceImpl(VoteRepository voteRepository, VoteMapper voteMapper, UserService userService,
                           @Lazy QuoteService quoteService) {
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
        this.userService = userService;
        this.quoteService = quoteService;
    }

    @Override
    public VoteDto createVote(Long userId, Long quoteId, String reaction) {
        User user = userService.getUserById(userId);
        Quote quote = quoteService.findQuoteById(quoteId);
        Reaction reactionType = Reaction.from(reaction)
                .orElseThrow(() -> new ValidationException("Unknown reaction: " + reaction));
        Vote vote = Vote.builder()
                .created(LocalDateTime.now())
                .reaction(reactionType)
                .quote(quote)
                .user(user)
                .build();
        return voteMapper.toVoteDto(voteRepository.save(vote));
    }

    @Override
    public List<VoteDto> getVotesByQuote(Long quoteId, Pageable pageable) {
        try {
            List<Vote> votes = voteRepository.findByQuoteId(quoteId, pageable);
            return voteMapper.toVoteDtoList(votes);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("Vote with id %s is not found", quoteId));
        }
    }

    @Override
    public void setScore(List<Quote> quotes) {
        List<Vote> votes = voteRepository.findAllByQuoteIn(quotes);
        Map<Long, Long> quotePopularity = votes.stream()
                .collect(groupingBy(vote -> vote.getQuote().getId(), summingLong(vote -> {
                    if (vote.getReaction().equals(Reaction.UPVOTE)) {
                        return 1;
                    } else if (vote.getReaction().equals(Reaction.DOWNVOTE)) {
                        return -1;
                    } else {
                        return 0;
                    }
                })));
        quotes.forEach(quote -> quote.setScore(quotePopularity.getOrDefault(quote.getId(), 0L)));
    }
}