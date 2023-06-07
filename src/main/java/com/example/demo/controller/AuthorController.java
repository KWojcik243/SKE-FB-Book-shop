package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public ResponseEntity<String> addAuthor(@RequestParam String name, @RequestParam String surname) {
        if (authorService.existsByNameAndSurname(name, surname)) {
            return ResponseEntity.badRequest().body("Author " + name + " " + surname + " already exists.");
        }

        authorService.addAuthor(name, surname);
        return ResponseEntity.ok().body("Author " + name + " " + surname + " added successfully.");
    }

    @DeleteMapping("/delete/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int authorId) {
        boolean removed = authorService.deleteAuthor(authorId);
        if (removed) {
            return ResponseEntity.ok("Author with id " + authorId + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id " + authorId + " not found.");
        }
    }
}
