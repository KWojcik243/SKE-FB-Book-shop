package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;

import java.util.List;

public interface AuthorService {
    public Author getAuthorById(int id);

    public List<Author> getAuthors();

    public void addAuthor(String name, String surname);

    public boolean existsByNameAndSurname(String name, String surname);

    public boolean deleteAuthor(int authorId);

    public boolean updateAuthor(int authorId, AuthorDTO authorDTO);
}
