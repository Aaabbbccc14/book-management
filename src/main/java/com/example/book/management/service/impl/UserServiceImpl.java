package com.example.book.management.service.impl;

import com.example.book.management.config.JwtService;
import com.example.book.management.dto.LoginRequest;
import com.example.book.management.dto.LoginResponse;
import com.example.book.management.dto.UserRequest;
import com.example.book.management.dto.UserResponse;
import com.example.book.management.emailSender.EmailSenderService;
import com.example.book.management.entity.User;
import com.example.book.management.exception.BadRequestException;
import com.example.book.management.exception.NotFoundException;
import com.example.book.management.mapper.UserMapper;
import com.example.book.management.repository.UserRepository;
import com.example.book.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailSenderService senderService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


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

    @Override
    public void register(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException("this email is already taken!: "+ request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(encoder.encode(request.getPassword()));
        String confirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(confirmCode);
        userRepository.save(user);
        senderService.sendEmail(user.getEmail(), "Confirm your email", "http://localhost:8099/user/confirm/" + confirmCode);
    }

    @Override
    public String confirm(String confirmCode) {
        Optional<User> user = userRepository.findByConfirmCode(confirmCode);
        if (user.isPresent()){
            user.get().setConfirmCode("authenticated");
            userRepository.save(user.get());
            return "successfully confirmed!";
        }

        return "user not found!";
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty())
            throw new NotFoundException("user not found with this email: "+ request.getEmail()+" !", HttpStatus.NOT_FOUND);
        if (!user.get().getConfirmCode().equals("authenticated")){
            throw new BadRequestException("please confirm your email!");
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        }catch (Exception e){
            throw new BadRequestException("bad credentials!");
        }
        String token = jwtService.generateToken(user.get());
        return new LoginResponse(user.get().getId(), user.get().getEmail(), token);
    }
}
