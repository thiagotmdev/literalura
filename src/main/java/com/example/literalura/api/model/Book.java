package com.example.literalura.api.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Integer downloads;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public void setAuthor(Author author) {
    }

    // Getters and Setters
}
