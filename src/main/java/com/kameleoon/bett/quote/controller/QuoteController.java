package com.kameleoon.bett.quote.controller;

import com.kameleoon.bett.quote.dto.QuoteDto;
import com.kameleoon.bett.quote.dto.QuoteNewDto;
import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.quote.service.QuoteService;
import com.kameleoon.bett.vote.dto.VoteDto;
import com.kameleoon.bett.vote.service.VoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private static final String QUOTE_ID_PATH_VARIABLE_KEY = "quoteId";
    private static final String USER_ID_PATH_VARIABLE_KEY = "userId";
    private final QuoteService quoteService;
    private final VoteService voteService;

    @PostMapping("/{userId}")
    public QuoteDto createQuote(@PathVariable(USER_ID_PATH_VARIABLE_KEY) Long userId,
                                @RequestBody @Valid QuoteNewDto quoteNewDto) {
        return quoteService.createQuote(userId, quoteNewDto);
    }

    @GetMapping("/{quoteId}")
    public QuoteShortDto getQuote(@PathVariable(QUOTE_ID_PATH_VARIABLE_KEY) Long quoteId) {
        return quoteService.getQuote(quoteId);
    }

    @GetMapping
    public List<QuoteShortDto> getAllQuotes(@RequestParam(required = false, defaultValue = "0")
                                            @PositiveOrZero int from,
                                            @RequestParam(required = false, defaultValue = "10")
                                            @Positive int size) {
        return quoteService.getAllQuotes(PageRequest.of(from, size));
    }

    @GetMapping("/top")
    public List<QuoteShortDto> getTopQuotes(@RequestParam(required = false, defaultValue = "0")
                                            @PositiveOrZero int from,
                                            @RequestParam(required = false, defaultValue = "10")
                                            @Positive int size) {
        return quoteService.getTopQuotes(PageRequest.of(from, size));
    }

    @PatchMapping("/{userId}/{quoteId}")
    public QuoteDto updateQuote(@PathVariable(USER_ID_PATH_VARIABLE_KEY) Long userId,
                                @PathVariable(QUOTE_ID_PATH_VARIABLE_KEY) Long quoteId,
                                @Valid @RequestBody QuoteNewDto updateQuoteDto) {
        return quoteService.updateQuote(userId, quoteId, updateQuoteDto);
    }

    @DeleteMapping("/{userId}/{quoteId}")
    public void deleteQuote(@PathVariable(USER_ID_PATH_VARIABLE_KEY) Long userId,
                            @PathVariable(QUOTE_ID_PATH_VARIABLE_KEY) Long quoteId) {
        quoteService.deleteQuote(userId, quoteId);
    }

    @PostMapping("/{userId}/{quoteId}/{reaction}")
    public VoteDto createVote(@PathVariable(USER_ID_PATH_VARIABLE_KEY) Long userId,
                              @PathVariable(QUOTE_ID_PATH_VARIABLE_KEY) Long quoteId,
                              @PathVariable(name = "reaction") String reaction) {
        return voteService.createVote(userId, quoteId, reaction);
    }

    @GetMapping("/{quoteId}/votes")
    public List<VoteDto> getVotesByQuote(@PathVariable(QUOTE_ID_PATH_VARIABLE_KEY) Long quoteId,
                                         @RequestParam(required = false, defaultValue = "0")
                                         @PositiveOrZero int from,
                                         @RequestParam(required = false, defaultValue = "10")
                                         @Positive int size) {
        return voteService.getVotesByQuote(quoteId, PageRequest.of(from, size));
    }
}