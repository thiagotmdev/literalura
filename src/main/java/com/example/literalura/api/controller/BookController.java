package com.example.literalura.api.controller;

import com.example.literalura.api.model.Author;
import com.example.literalura.api.model.Book;
import com.example.literalura.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping
    public List<Book> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/authors")
    public List<Author> listAuthors() {
        return bookService.listAuthors();
    }

    @GetMapping("/authors/year")
    public List<Author> listAuthorsByYear(@RequestParam int year) {
        return bookService.listAuthorsByYear(year);
    }

    @GetMapping("/language")
    public List<Book> listBooksByLanguage(@RequestParam String language) {
        return bookService.listBooksByLanguage(language);
    }
}
