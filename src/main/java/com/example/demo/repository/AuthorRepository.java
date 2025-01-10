package com.example.demo.repository;

import com.example.demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE (a.deathYear IS NULL OR a.deathYear >= :year) AND a.birthYear <= :year")
    List<Author> findAuthorsAliveInYear(Integer year);
}