package com.kameleoon.bett.vote.service;

import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.vote.dto.VoteDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoteService {
    VoteDto createVote(Long userId, Long quoteId, String reaction);

    List<VoteDto> getVotesByQuote(Long quoteId, Pageable pageable);

    void setScore(List<Quote> quotes);
}