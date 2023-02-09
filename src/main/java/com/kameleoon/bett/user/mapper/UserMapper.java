package com.kameleoon.bett.user.mapper;

import com.kameleoon.bett.user.dto.UserDto;
import com.kameleoon.bett.user.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}