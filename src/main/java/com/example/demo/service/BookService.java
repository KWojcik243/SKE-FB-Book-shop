package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    public Book getBookById(int id);

    public List<Book> getBooks();

    public void addBook(BookDTO bookDTO);

    public boolean editBook(int bookId,
                            BookDTO bookDTO);


    public List<Book> getBooksByAuthorId(int id);

    public boolean existsByTitle(String title);

    public boolean deleteBook(int bookId);
}
