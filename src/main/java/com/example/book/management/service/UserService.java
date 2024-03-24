package com.example.book.management.service;

import com.example.book.management.dto.UserRequest;
import com.example.book.management.dto.UserResponse;

import java.util.List;

public interface UserService {
    void add(UserRequest request);

    List<UserResponse> all();

    UserResponse byEmail(String email);
}
