package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(@RequestParam int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @Override
    public void addBook(String title, String pngPath, int ageGroup, float rating, long isbn, int amount) {
        Book book = new Book(title, pngPath, ageGroup, rating, isbn, amount);
        bookRepository.save(book);
    }
}
