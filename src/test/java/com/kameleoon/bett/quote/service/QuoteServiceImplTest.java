package com.kameleoon.bett.quote.service;

import com.kameleoon.bett.exeption.ValidationException;
import com.kameleoon.bett.quote.dto.QuoteShortDto;
import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.user.service.UserService;
import com.kameleoon.bett.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.kameleoon.bett.quote.service.QuoteTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class QuoteServiceImplTest {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        userService.createUser(userDto1);
        userService.createUser(userDto2);
        quoteService.createQuote(1L, quoteNewDto1);
        quoteService.createQuote(2L, quoteNewDto2);
    }

    @Test
    void createQuoteTest() {
        Quote quote = quoteService.findQuoteById(1L);
        assertThat(quote.getId(), equalTo(1L));
        assertThat(quote.getDescription(), equalTo(quoteNewDto1.getDescription()));
        assertThat(quote.getAuthor().getId(), equalTo(1L));
        assertThat(quote.getScore(), equalTo(0L));
    }

    @Test
    void getQuoteTest() {
        QuoteShortDto quoteShortDto = quoteService.getQuote(1L);
        assertThat(quoteShortDto.getDescription(), equalTo(quoteNewDto1.getDescription()));
        assertThat(quoteShortDto.getAuthor().getName(), equalTo(userDto1.getName()));
        assertThat(quoteShortDto.getScore(), equalTo(0L));
    }

    @Test
    void getQuoteWithWrongIdTest() {
        assertThrows(NoSuchElementException.class, () -> quoteService.getQuote(10L));
    }

    @Test
    void getAllQuotesTest() {
        List<QuoteShortDto> quoteList = quoteService.getAllQuotes(PageRequest.of(0, 10));
        assertThat(quoteList.size(), equalTo(2));
    }

    @Test
    void getTopQuotesTest() {
        voteService.createVote(1L, 1L, "UPVOTE");
        voteService.createVote(2L, 1L, "UPVOTE");
        List<QuoteShortDto> quoteList = quoteService.getTopQuotes(PageRequest.of(0, 10));
        assertThat(quoteList.get(0).getScore(), equalTo(2L));
        assertThat(quoteList.size(), equalTo(2));
    }

    @Test
    void updateQuoteTest() {
        quoteNewDto1.setDescription("descriptionChanged");
        quoteService.updateQuote(1L, 1L, quoteNewDto1);
        Quote quote = quoteService.findQuoteById(1L);
        assertThat(quote.getDescription(), equalTo("descriptionChanged"));
    }

    @Test
    void updateQuoteWithWrongAuthorTest() {
        quoteNewDto1.setDescription("descriptionChanged");
        assertThrows(ValidationException.class, () -> quoteService.updateQuote(2L, 1L, quoteNewDto1));
    }
}