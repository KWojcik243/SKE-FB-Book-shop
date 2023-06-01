package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            authorRepository.delete(author);
            return true;
        }
        return false;
    }

}
