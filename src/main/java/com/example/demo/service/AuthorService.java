package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthorService {
    public Author getAuthorById(@RequestParam int id);
    public List<Author> getAuthors();

    public void addAuthor(@RequestParam String name, @RequestParam String surname);
}
