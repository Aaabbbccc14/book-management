package com.example.book.management.mapper;

import com.example.book.management.dto.BookResponse;
import com.example.book.management.entity.Book;

import java.util.List;

public interface BookMapper {
    List<BookResponse> toDtoS(List<Book> all);

    BookResponse toDto(Book book);
}
