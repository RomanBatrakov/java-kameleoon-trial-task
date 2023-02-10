package com.kameleoon.bett.quote.dto;

import com.kameleoon.bett.user.dto.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteShortDto {
    private String description;
    private UserShortDto author;
    private Long score;
}