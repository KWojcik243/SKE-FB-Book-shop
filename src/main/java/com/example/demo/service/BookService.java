package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    public Book getBookById(int id);

    public List<Book> getBooks();

    public void addBook(String title,
                        String pngPath,
                        int ageGroup,
                        float rating,
                        long isbn,
                        int amount,
                        List<Integer> authorIds);

    public List<Book> getBooksByAuthorId(int id);

    public boolean existsByTitle(String title);

    public boolean deleteBook(int bookId);
}
