package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/book")
    public Book getBookByID(@RequestParam int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/book")
    public void addBook(@RequestParam String title,
                        @RequestParam String pngPath,
                        @RequestParam int ageGroup,
                        @RequestParam float rating,
                        @RequestParam long isbn,
                        @RequestParam int amount,
                        @RequestParam List<Integer> authorIds) {
        bookService.addBook(title, pngPath, ageGroup, rating, isbn, amount, authorIds);

    }

    @GetMapping("/booksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam int authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

}
