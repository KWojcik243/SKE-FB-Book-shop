package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {
    public Book getBookById(@RequestParam int id);
    public List<Book> getBooks();

    public void addBook(@RequestParam String title,
                        @RequestParam String pngPath,
                        @RequestParam int ageGroup,
                        @RequestParam float rating,
                        @RequestParam long isbn,
                        @RequestParam int amount,
                        @RequestParam List<Integer> authorIds);

    public List<Book> getBooksByAuthorId(@RequestParam int id);
}
