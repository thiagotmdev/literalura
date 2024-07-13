package com.example.literalura.api.service;

import com.example.literalura.api.model.Author;
import com.example.literalura.api.model.Book;
import com.example.literalura.api.repository.AuthorRepository;
import com.example.literalura.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GutendexService gutendexService;

    public List<Book> searchBooksByTitle(String title) {
        String response = gutendexService.searchBooksByTitle(title);
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray results = jsonResponse.getJSONArray("results");

        return results.toList().stream()
                .map(result -> {
                    JSONObject bookJson = new JSONObject(result);
                    Book book = new Book();
                    book.setTitle(bookJson.getString("title"));
                    book.setLanguage(bookJson.getString("language"));
                    book.setDownloads(bookJson.getInt("download_count"));

                    Author author = authorRepository.findByName(bookJson.getJSONArray("authors").getJSONObject(0).getString("name"))
                            .orElseGet(() -> {
                                Author newAuthor = new Author();
                                newAuthor.setName(bookJson.getJSONArray("authors").getJSONObject(0).getString("name"));
                                return authorRepository.save(newAuthor);
                            });
                    book.setAuthor(author);

                    return bookRepository.save(book);
                })
                .collect(Collectors.toList());
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsByYear(int year) {
        return authorRepository.findAll().stream()
                .filter(author -> (author.getBirthYear() != null && author.getBirthYear() <= year) &&
                        (author.getDeathYear() == null || author.getDeathYear() >= year))
                .collect(Collectors.toList());
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getLanguage().equalsIgnoreCase(language))
                .collect(Collectors.toList());
    }
}
