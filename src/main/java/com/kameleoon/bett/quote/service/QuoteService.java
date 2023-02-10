package com.kameleoon.bett.quote.service;

import com.kameleoon.bett.quote.dto.QuoteDto;
import com.kameleoon.bett.quote.dto.QuoteNewDto;
import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.quote.model.Quote;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {
    QuoteDto createQuote(Long userId, QuoteNewDto quoteNewDto);

    QuoteShortDto getQuote(Long quoteId);

    List<QuoteShortDto> getAllQuotes(Pageable pageable);

    List<QuoteShortDto> getTopQuotes(Pageable pageable);

    QuoteDto updateQuote(Long userId, Long quoteId, QuoteNewDto updateQuoteDto);

    void deleteQuote(Long userId, Long quoteId);

    Quote findQuoteById(Long quoteId);
}
