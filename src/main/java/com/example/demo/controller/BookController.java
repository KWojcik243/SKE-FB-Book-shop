package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<String> addBook(BookDTO bookDTO) {
        if (bookService.existsByTitle(bookDTO.getTitle())) {
            return ResponseEntity.badRequest().body("Book with title " + bookDTO.getTitle() + " already exists.");
        }

        bookService.addBook(bookDTO);
        return ResponseEntity.ok().body("Book " + bookDTO.getTitle() + " added successfully.");
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> editBook(@PathVariable int bookId, BookDTO bookDTO) {
        bookService.editBook(bookId, bookDTO);
        return ResponseEntity.ok().body("Book " + bookDTO.getTitle() + " edited successfully.");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        boolean removed = bookService.deleteBook(bookId);
        if (removed) {
            return ResponseEntity.ok("Book with id " + bookId + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + bookId + " not found.");
        }
    }

    @GetMapping("/by-author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable int authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }
}
