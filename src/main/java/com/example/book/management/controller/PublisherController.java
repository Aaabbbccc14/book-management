package com.example.book.management.controller;

import com.example.book.management.dto.BookResponse;
import com.example.book.management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<BookResponse> publisherBooks(@RequestParam String publisherEmail){
        return bookService.getPublisherBooks(publisherEmail);
    }

}
