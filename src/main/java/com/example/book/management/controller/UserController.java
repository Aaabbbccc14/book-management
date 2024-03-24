package com.example.book.management.controller;

import com.example.book.management.dto.UserRequest;
import com.example.book.management.dto.UserResponse;
import com.example.book.management.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody UserRequest request){
        userService.add(request);
    }
    @GetMapping("/all")
    public List<UserResponse> all(){
        return userService.all();
    }
    @GetMapping("/byEmail")
    public UserResponse byEmail(@RequestParam String userEmail){
        return userService.byEmail(userEmail);
    }

}
