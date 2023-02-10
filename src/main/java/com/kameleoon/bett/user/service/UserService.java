package com.kameleoon.bett.user.service;

import com.kameleoon.bett.user.dto.UserDto;
import com.kameleoon.bett.user.model.User;

public interface UserService {
    UserDto createUser(UserDto userDto);

    User getUserById(Long userId);
}