package com.example.book.management.service.impl;

import com.example.book.management.dto.BookRequest;
import com.example.book.management.entity.Author;
import com.example.book.management.entity.Book;
import com.example.book.management.entity.Publisher;
import com.example.book.management.repository.AuthorRepository;
import com.example.book.management.repository.BookRepository;
import com.example.book.management.repository.PublisherRepository;
import com.example.book.management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    @Override
    public void add(BookRequest request) {
        Book book = new Book();
        book.setName(request.getName());
        book.setISBN(request.getISBN());
        book.setAuthor(getAuthor(request.getAuthorFullName(), book));
        book.setPublisher(getPublisher(request.getPublisherEmail(), book));
        book.setAddedTime(LocalDateTime.now());
        bookRepository.save(book);

    }

    private Publisher getPublisher(String publisherEmail, Book book) {
        Optional<Publisher> publisher = publisherRepository.findByEmail(publisherEmail);
        List<Book> publisherBooks = new ArrayList<>();
        if (publisher.isPresent()){
            publisherBooks = publisher.get().getBooks();
            publisherBooks.add(book);
            publisherRepository.save(publisher.get());
            return publisher.get();
        }
        else {
            Publisher publisher1 = new Publisher();
            publisher1.setName("");
            publisher1.setEmail(publisherEmail);
            publisherBooks.add(book);
            publisherRepository.save(publisher1);
            return publisher1;
        }
    }

    private Author getAuthor(String authorFullName, Book book) {
        Optional<Author> author = authorRepository.findByFullName(authorFullName);
        List<Book> authorBooks = new ArrayList<>();
        if (author.isPresent()){
            authorBooks = author.get().getBooks();
            authorBooks.add(book);
            authorRepository.save(author.get());
            return author.get();
        }else {
            Author author1 = new Author();
            author1.setFullName(authorFullName);
            authorBooks.add(book);
            authorRepository.save(author1);
            return author1;
        }
    }
}
