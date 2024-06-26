package com.example.book.management.repository;

import com.example.book.management.entity.Book;
import com.example.book.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmCode(String confirmCode);
}
