package com.example.book.management.controller;

import com.example.book.management.dto.BookRequest;
import com.example.book.management.dto.BookResponse;
import com.example.book.management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public void add(@RequestBody BookRequest request){
        bookService.add(request);
    }
    @GetMapping("/all")
    public List<BookResponse> all(){
        return bookService.all();
    }
    @GetMapping("/{bookId}")
    public BookResponse byId(@PathVariable Long bookId){
        return bookService.getById(bookId);
    }
}
