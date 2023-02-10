package com.kameleoon.bett.quote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuoteNewDto {
    @NotBlank(message = " is blank or null")
    @Size(min = 10, max = 500, message = " has wrong size")
    private String description;
}