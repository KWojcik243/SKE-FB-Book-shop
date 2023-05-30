package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book getBookById(@RequestParam int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(@RequestParam String title,
                        @RequestParam String pngPath,
                        @RequestParam int ageGroup,
                        @RequestParam float rating,
                        @RequestParam long isbn,
                        @RequestParam int amount,
                        List<Integer> authorIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);
        Book book = new Book(title, pngPath, ageGroup, rating, isbn, amount);
        if (!authors.isEmpty()) {
            book.setAuthors(authors);
            bookRepository.save(book);
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            List<Book> books = bookRepository.findByAuthorsIn(Collections.singletonList(author));
            return books;
        } else {
            // Handle the case when the author is not found
            return Collections.emptyList();
        }
    }


}
