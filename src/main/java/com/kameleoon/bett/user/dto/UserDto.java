package com.kameleoon.bett.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = " is blank or null")
    @Size(max = 50, message = " is to long")
    private String name;
    @NotBlank(message = " is blank or null")
    @Email(message = " is incorrect")
    private String email;
}
