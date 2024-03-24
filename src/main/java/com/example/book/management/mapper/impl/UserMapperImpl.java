package com.example.book.management.mapper.impl;

import com.example.book.management.dto.UserResponse;
import com.example.book.management.entity.User;
import com.example.book.management.mapper.BookMapper;
import com.example.book.management.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final BookMapper bookMapper;
    @Override
    public List<UserResponse> toDtoS(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: all)
            userResponses.add(toDto(user));
        return userResponses;
    }

    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setUserBooks(bookMapper.toDtoS(user.getBooks()));

        return userResponse;
    }
}
