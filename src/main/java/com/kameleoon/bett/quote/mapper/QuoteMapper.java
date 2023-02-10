package com.kameleoon.bett.quote.mapper;

import com.kameleoon.bett.quote.dto.QuoteDto;
import com.kameleoon.bett.quote.dto.QuoteNewDto;
import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.quote.model.Quote;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QuoteMapper {
    QuoteDto toQuoteDto(Quote quote);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Quote partialUpdate(QuoteNewDto updateQuoteDto, @MappingTarget Quote quote);

    Quote toQuoteNewDto(QuoteNewDto quoteNewDto);

    QuoteShortDto toQuoteShortDto(Quote quote);

    List<QuoteShortDto> toQuoteShortDtoList(Page<Quote> quotes);
}