package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }


    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
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
}
