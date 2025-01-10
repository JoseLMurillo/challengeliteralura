package com.example.demo;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	@Override
	public void run(String... args) throws Exception {

	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Menu menu = context.getBean(Menu.class);
		menu.showMenu();
	}
}
