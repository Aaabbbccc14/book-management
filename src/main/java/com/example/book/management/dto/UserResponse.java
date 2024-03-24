package com.example.book.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private List<BookResponse> userBooks;
}
