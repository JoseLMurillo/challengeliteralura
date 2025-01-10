package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Author;
import com.example.demo.repository.AuthorRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public void save(Book book) {
        // Verificar si el autor ya existe en la base de datos
        Author author = book.getAuthor();
        if (author != null && author.getId() == null) {
            // Si el autor no tiene ID, significa que no está persistido
            authorRepository.save(author);
        }

        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public List<Book> findByLanguage(String language) {
        return bookRepository.findByLanguagesContainingIgnoreCase(language);
    }
}