package com.example.book.management.mapper;

import com.example.book.management.dto.UserResponse;
import com.example.book.management.entity.User;

import java.util.List;

public interface UserMapper {
    List<UserResponse> toDtoS(List<User> all);

    UserResponse toDto(User user);
}
