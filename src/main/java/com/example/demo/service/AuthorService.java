package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthorService {
    public Author getAuthorById(int id);
    public List<Author> getAuthors();

    public void addAuthor(String name, String surname);

    public boolean existsByNameAndSurname(String name, String surname);
}
