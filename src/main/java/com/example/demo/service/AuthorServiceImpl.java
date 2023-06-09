package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
    }

    @Override
    public boolean existsByNameAndSurname(String name, String surname) {
        return authorRepository.existsByNameAndSurname(name, surname);
    }

    @Override
    public boolean deleteAuthor(int authorId) {
        if (authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return true;
        } else return false;
    }

    @Override
    public boolean updateAuthor(int authorId, AuthorDTO authorDTO) {
        if (authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return true;
        } else return false;
    }
}
