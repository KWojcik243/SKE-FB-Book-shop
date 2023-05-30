package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {
    public Book getBookById(@RequestParam int id);
    public List<Book> getBooks();

    public void addBook(String title, String pngPath, int ageGroup, float rating, long isbn, int amount);
}
