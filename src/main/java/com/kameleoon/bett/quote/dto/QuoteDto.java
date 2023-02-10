package com.kameleoon.bett.quote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kameleoon.bett.user.dto.UserShortDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {
    private Long id;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private UserShortDto author;
    private Long score;
}