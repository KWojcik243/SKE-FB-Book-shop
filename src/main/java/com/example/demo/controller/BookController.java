package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBook")
    public ResponseEntity<?> getBookByID(@RequestParam int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }


    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestParam String title,
                                          @RequestParam String pngPath,
                                          @RequestParam int ageGroup,
                                          @RequestParam float rating,
                                          @RequestParam long isbn,
                                          @RequestParam int amount,
                                          @RequestParam List<Integer> authorIds) {
        if (bookService.existsByTitle(title)) {
            return ResponseEntity.badRequest().body("Book with title " + title + " already exists.");
        }

        bookService.addBook(title, pngPath, ageGroup, rating, isbn, amount, authorIds);
        return ResponseEntity.ok().body("Book " + title + " added successfully.");
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@RequestParam int bookId) {
        boolean removed = bookService.deleteBook(bookId);
        if (removed) {
            return ResponseEntity.ok("Book with id " + bookId + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + bookId + " not found.");
        }
    }


    @GetMapping("/booksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam int authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

}
