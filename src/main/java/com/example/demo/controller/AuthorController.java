package com.example.demo.controller;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Author>> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        String uri = "/authors/" + id;
        EntityModel<Author> authorModel = EntityModel.of(author,
                Link.of(getBaseUrl() + uri).withSelfRel(),
                Link.of(getBaseUrl() + uri).withRel("PUT"),
                Link.of(getBaseUrl() + uri).withRel("DELETE")
        );

        return ResponseEntity.ok(authorModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Author>>> getAuthors() {
        List<Author> authors = authorService.getAuthors();

        List<EntityModel<Author>> authorModels = authors.stream()
                .map(author -> {
                    int authorId = author.getId();
                    String uri = "/authors/" + authorId;
                    EntityModel<Author> authorModel = EntityModel.of(author,
                            Link.of(getBaseUrl() + uri).withSelfRel(),
                            Link.of(getBaseUrl() + uri).withRel("PUT"),
                            Link.of(getBaseUrl() + uri).withRel("DELETE"));
                    return authorModel;
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Author>> authorsResource = CollectionModel.of(authorModels);
        authorsResource.add(Link.of(getBaseUrl() + "/authors").withSelfRel());

        return ResponseEntity.ok(authorsResource);
    }


    @PostMapping
    public ResponseEntity<String> addAuthor(@RequestParam String name, @RequestParam String surname) {
        if (authorService.existsByNameAndSurname(name, surname)) {
            return ResponseEntity.badRequest().body("Author " + name + " " + surname + " already exists.");
        }

        authorService.addAuthor(name, surname);
        return ResponseEntity.ok().body("Author " + name + " " + surname + " added successfully.");
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int authorId) {
        boolean removed = authorService.deleteAuthor(authorId);
        if (removed) {
            return ResponseEntity.ok("Author with id " + authorId + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id " + authorId + " not found.");
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthor(@PathVariable int authorId, @RequestBody AuthorDTO authorDTO) {
        boolean updated = authorService.updateAuthor(authorId, authorDTO);
        if (updated) {
            return ResponseEntity.ok("Author with id " + authorId + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating author with id " + authorId + ".");
        }
    }

    private String getBaseUrl() {
        String requestUrl = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        return requestUrl.replaceFirst("/authors.*", "");
    }
}
