package com.example.book.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String name;
    private String ISBN;
    private String authorFullName;
    private String publisherEmail;
}
