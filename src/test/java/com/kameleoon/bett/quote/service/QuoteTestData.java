package com.kameleoon.bett.quote.service;

import com.kameleoon.bett.quote.dto.QuoteNewDto;
import com.kameleoon.bett.user.dto.UserDto;

public class QuoteTestData {
    public static final UserDto userDto1 = UserDto.builder().name("user1").email("user1@mail.ru").build();
    public static final UserDto userDto2 = UserDto.builder().name("user2").email("user2@mail.ru").build();
    public static final QuoteNewDto quoteNewDto1 = QuoteNewDto.builder().description("description1").build();
    public static final QuoteNewDto quoteNewDto2 = QuoteNewDto.builder().description("description2").build();
}