package com.example.book.management.service;

import com.example.book.management.dto.BookRequest;
import com.example.book.management.dto.BookResponse;

import java.util.List;

public interface BookService {
    void add(BookRequest request);

    List<BookResponse> all();

    BookResponse getById(Long bookId);

    List<BookResponse> getPublisherBooks(String publisherId);
}
