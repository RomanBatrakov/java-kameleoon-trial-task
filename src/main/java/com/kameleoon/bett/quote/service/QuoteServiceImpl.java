package com.kameleoon.bett.quote.service;

import com.kameleoon.bett.exeption.ValidationException;
import com.kameleoon.bett.quote.dao.QuoteRepository;
import com.kameleoon.bett.quote.dto.QuoteDto;
import com.kameleoon.bett.quote.dto.QuoteNewDto;
import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.quote.mapper.QuoteMapper;
import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.user.model.User;
import com.kameleoon.bett.user.service.UserService;
import com.kameleoon.bett.vote.service.VoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;
    private final UserService userService;
    private final VoteService voteService;

    @Override
    public QuoteDto createQuote(Long userId, QuoteNewDto quoteNewDto) {
        User user = userService.getUserById(userId);
        Quote quote = quoteMapper.toQuoteNewDto(quoteNewDto);
        quote.setCreated(LocalDateTime.now());
        quote.setAuthor(user);
        quote.setScore(0L);
        return quoteMapper.toQuoteDto(quoteRepository.save(quote));
    }

    @Override
    public QuoteShortDto getQuote(Long quoteId) {
        Quote quote = findQuoteById(quoteId);
        voteService.setScore(Collections.singletonList(quote));
        return quoteMapper.toQuoteShortDto(quote);
    }

    @Override
    public List<QuoteShortDto> getAllQuotes(Pageable pageable) {
        Page<Quote> quotes = quoteRepository.findAll(pageable);
        voteService.setScore(quotes.getContent());
        return quoteMapper.toQuoteShortDtoList(quotes);
    }

    @Override
    public List<QuoteShortDto> getTopQuotes(Pageable pageable) {
        return getAllQuotes(pageable).stream()
                .sorted(Comparator.comparingLong(QuoteShortDto::getScore).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public QuoteDto updateQuote(Long userId, Long quoteId, QuoteNewDto updateQuoteDto) {
        User user = userService.getUserById(userId);
        Quote quote = findQuoteById(quoteId);
        if (user.equals(quote.getAuthor())) {
            Quote updatedQuote = quoteMapper.partialUpdate(updateQuoteDto, quote);
            voteService.setScore(Collections.singletonList(updatedQuote));
            return quoteMapper.toQuoteDto(updatedQuote);
        } else {
            throw new ValidationException("Wrong author.");
        }
    }

    @Override
    public void deleteQuote(Long userId, Long quoteId) {
        User user = userService.getUserById(userId);
        Quote quote = findQuoteById(quoteId);
        authorValidation(user, quote);
        quoteRepository.deleteById(quoteId);
    }

    @Override
    public Quote findQuoteById(Long quoteId) {
        try {
            return quoteRepository.findById(quoteId).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("Quote with id %s is not found", quoteId));
        }
    }

    private void authorValidation(User user, Quote quote) {
        boolean valid = user.equals(quote.getAuthor());
        if (!valid) throw new ValidationException("Wrong author.");
    }
}