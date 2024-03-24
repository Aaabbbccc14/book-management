package com.example.book.management.service.impl;

import com.example.book.management.dto.UserRequest;
import com.example.book.management.dto.UserResponse;
import com.example.book.management.entity.User;
import com.example.book.management.exception.BadRequestException;
import com.example.book.management.exception.NotFoundException;
import com.example.book.management.mapper.UserMapper;
import com.example.book.management.repository.UserRepository;
import com.example.book.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public void add(UserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent())
            throw new BadRequestException("this email is already taken!: "+ request.getEmail());
        User user1 = new User();
        user1.setEmail(request.getEmail());
        user1.setFirstname(request.getFirstname());
        user1.setLastname(request.getLastname());
        userRepository.save(user1);
    }

    @Override
    public List<UserResponse> all() {
        return userMapper.toDtoS(userRepository.findAll());
    }

    @Override
    public UserResponse byEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new NotFoundException("user not found with this email: "+ email+" !", HttpStatus.NOT_FOUND);
        return userMapper.toDto(user.get());
    }
}
