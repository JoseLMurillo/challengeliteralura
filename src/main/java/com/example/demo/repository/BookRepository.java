package com.example.demo.repository;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);
    List<Book> findByLanguagesContainingIgnoreCase(String language);
    List<Book> findByAuthorId(Long authorId);
}