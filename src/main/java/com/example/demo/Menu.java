package com.example.demo;

import com.example.demo.dto.BookApiResponse;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.services.ApiService;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ApiService apiService;

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Search book by title");
            System.out.println("2. List all books");
            System.out.println("3. List all authors");
            System.out.println("4. List authors alive in a specific year");
            System.out.println("5. List books by language");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listAuthorsAliveInYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void searchBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        Optional<Book> existingBook = bookService.findByTitle(title);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            System.out.println("\nBook found in database:");
            printBookDetails(book);
        } else {
            System.out.println("\nSearching in Gutendex API...");
            String apiResponse = apiService.getBookByName(title);
            BookApiResponse bookData = apiService.parseBookResponse(apiResponse);

            if (bookData == null) {
                System.out.println("Book not found!");
                return;
            }

            // Create and save new author if necessary
            Author author = new Author();
            author.setName(bookData.getAuthorName());
            author.setBirthYear(bookData.getAuthorBirthYear());
            author.setDeathYear(bookData.getAuthorDeathYear());

            // Create and save new book
            Book newBook = new Book();
            newBook.setTitle(bookData.getTitle());
            newBook.setLanguages(bookData.getLanguages());
            newBook.setAuthor(author);
            bookService.save(newBook);

            System.out.println("Book saved to database:");
            printBookDetails(newBook);
        }
    }

    private void listBooks() {
        List<Book> books = bookService.findAll();

        System.out.println("\nAll books in database:");
        for(Book book: books){
            System.out.println("Title: "+book.getTitle());
        }
    }

    private void listAuthors() {
        List<Author> authors = authorService.findAll();
        System.out.println("\nAll authors in database:");
        for(Author author: authors){
            System.out.println("Name: "+author.getName());
            System.out.println("Birth Year: "+author.getBirthYear());
            System.out.println("Death Year: "+author.getDeathYear());
        }
    }


    private void listAuthorsAliveInYear() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        List<Author> authors = authorService.findAuthorsAliveInYear(year);
        System.out.println("\nAuthors alive in " + year + ":");
        for(Author author: authors){
            System.out.println("Name: "+author.getName());
            System.out.println("Birth Year: "+author.getBirthYear());
            System.out.println("Death Year: "+author.getDeathYear());
        }
    }

    private void listBooksByLanguage() {
        System.out.print("Enter language code (e.g., en, es): ");
        String language = scanner.nextLine();
        List<Book> books = bookService.findByLanguage(language);
        System.out.println("\nBooks in " + language + ":");
        for(Book book: books){
            System.out.println(book.getTitle());
        }
    }

    private void printBookDetails(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor().getName());
        System.out.println("Languages: " + String.join(", ", book.getLanguages()));
        System.out.println("------------------------");
    }

    private void printAuthorDetails(Author author) {
        System.out.println("Name: " + author.getName());
        System.out.println("Birth Year: " + (author.getBirthYear() != null ? author.getBirthYear() : "Unknown"));
        System.out.println("Death Year: " + (author.getDeathYear() != null ? author.getDeathYear() : "Unknown"));
        System.out.println("Number of Books: " + author.getBooks().size());
        System.out.println("------------------------");
    }
}