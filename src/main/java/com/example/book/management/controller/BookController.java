package com.example.book.management.controller;

import com.example.book.management.dto.BookRequest;
import com.example.book.management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public void add(@RequestBody BookRequest request){
        bookService.add(request);
    }
}
