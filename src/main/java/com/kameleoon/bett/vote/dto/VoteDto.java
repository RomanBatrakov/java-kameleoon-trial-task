package com.kameleoon.bett.vote.dto;

import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.user.dto.UserShortDto;
import com.kameleoon.bett.vote.model.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private UserShortDto user;
    private QuoteShortDto quote;
    private LocalDateTime created;
    private Reaction reaction;
}