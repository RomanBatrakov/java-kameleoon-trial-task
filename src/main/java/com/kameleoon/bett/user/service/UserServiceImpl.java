package com.kameleoon.bett.user.service;

import com.kameleoon.bett.exeption.ValidationException;
import com.kameleoon.bett.user.dao.UserRepository;
import com.kameleoon.bett.user.dto.UserDto;
import com.kameleoon.bett.user.mapper.UserMapper;
import com.kameleoon.bett.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            User user = userRepository.save(userMapper.toUser(userDto));
            return userMapper.toUserDto(user);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException(String.format("User email %s is already exist", userDto.getEmail()));
        }
    }

    @Override
    public User getUserById(Long userId) {
        try {
            return userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("User with id %s is not found", userId));
        }
    }
}