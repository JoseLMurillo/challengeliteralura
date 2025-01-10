package com.example.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Helper method to add a book
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    // Helper method to remove a book
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    public void setBirthYear(Integer authorBirthYear) {
        this.birthYear =authorBirthYear;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setDeathYear(Integer authorDeathYear) {
        this.deathYear = authorDeathYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }
}