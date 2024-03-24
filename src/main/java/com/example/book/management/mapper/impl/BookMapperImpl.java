package com.example.book.management.mapper.impl;

import com.example.book.management.dto.BookResponse;
import com.example.book.management.entity.Book;
import com.example.book.management.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public List<BookResponse> toDtoS(List<Book> all) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book: all)
            bookResponses.add(toDto(book));
        return bookResponses;
    }

    @Override
    public BookResponse toDto(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setAddedTime(book.getAddedTime());
        bookResponse.setName(book.getName());
        bookResponse.setISBN(book.getISBN());
        bookResponse.setPublisherEmail(book.getPublisher()!=null? book.getPublisher().getEmail(): null);
        bookResponse.setAuthorFullName(book.getAuthor()!=null? book.getAuthor().getFullName(): null);
        return bookResponse;
    }
}
