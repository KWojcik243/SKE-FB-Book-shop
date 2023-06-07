package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

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

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(String title, String pngPath, int ageGroup, float rating, long isbn, int amount, List<Integer> authorIds) {
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

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    @Override
    public boolean deleteBook(int bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
            return true;
        }
        return false;
    }

}
