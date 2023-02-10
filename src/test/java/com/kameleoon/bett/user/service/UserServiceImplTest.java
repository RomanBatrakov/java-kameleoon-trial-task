package com.kameleoon.bett.user.service;

import com.kameleoon.bett.exeption.ValidationException;
import com.kameleoon.bett.user.dto.UserDto;
import com.kameleoon.bett.user.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(
                null,
                "John",
                "john.doe@mail.com");
    }

    @Test
    void createUserTest() {
        userService.createUser(userDto);
        User user = userService.getUserById(1L);
        assertThat(user.getId(), equalTo(1L));
        assertThat(user.getName(), equalTo(userDto.getName()));
        assertThat(user.getEmail(), equalTo(userDto.getEmail()));
    }

    @Test
    void createUserWithSameEmailTest() {
        userService.createUser(userDto);
        assertThrows(ValidationException.class, () -> userService.createUser(userDto));
    }
}