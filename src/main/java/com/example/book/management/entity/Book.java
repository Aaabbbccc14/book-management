package com.example.book.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ISBN;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;
    private LocalDateTime addedTime;
}
