package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = {"http://127.0.0.1:5173"})
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Book> resource = getBookResource(book);
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Book>>> getBooks() {
        List<Book> books = bookService.getBooks();
        CollectionModel<EntityModel<Book>> resource = getBooksResource(books);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
        if (bookService.existsByTitle(bookDTO.getTitle())) {
            return ResponseEntity.badRequest().body("Book with title " + bookDTO.getTitle() + " already exists.");
        }

        bookService.addBook(bookDTO);
        return ResponseEntity.ok().body("Book " + bookDTO.getTitle() + " added successfully.");
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> editBook(@PathVariable int bookId, @RequestBody BookDTO bookDTO) {
        try {
            bookService.editBook(bookId, bookDTO);
            return ResponseEntity.ok().body("Book " + bookDTO.getTitle() + " edited successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.ok("Book with id " + bookId + " deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private EntityModel<Book> getBookResource(Book book) {
        EntityModel<Book> resource = EntityModel.of(book);
        resource.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withRel("PUT"));
        resource.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withRel("DELETE"));
        return resource;
    }

    private CollectionModel<EntityModel<Book>> getBooksResource(List<Book> books) {
        List<EntityModel<Book>> bookResources = books.stream()
                .map(this::getBookResource)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Book>> resource = CollectionModel.of(bookResources);
        resource.add(WebMvcLinkBuilder.linkTo(BookController.class).withSelfRel());
        return resource;
    }
}
