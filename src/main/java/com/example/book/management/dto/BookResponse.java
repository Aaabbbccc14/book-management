package com.example.book.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookResponse {
    private Long id;
    private String name;
    private String ISBN;
    private String authorFullName;
    private String publisherEmail;
    private LocalDateTime addedTime;
}
