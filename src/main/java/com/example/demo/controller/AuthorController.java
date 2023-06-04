package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getAuthor")
    public ResponseEntity<?> getAuthorByID(@RequestParam int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping("/getAuthors")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestParam String name, @RequestParam String surname) {
        if (authorService.existsByNameAndSurname(name, surname)) {
            return ResponseEntity.badRequest().body("Author " + name + " " + surname + " already exists.");
        }

        authorService.addAuthor(name, surname);
        return ResponseEntity.ok().body("Author " + name + " " + surname + " added successfully.");
    }

    @PostMapping("/deleteAuthor")
    public ResponseEntity<String> deleteAuthor(int authorId) {
        boolean removed = authorService.deleteAuthor(authorId);
        if (removed) {
            return ResponseEntity.ok("Author with id " + authorId + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id " + authorId + " not found.");
        }
        // TODO co jesli usuwamy autora i ksiazka zostaje bez autora? usuwac z bazy?
    }


}
