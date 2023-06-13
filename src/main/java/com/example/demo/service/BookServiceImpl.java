package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCategory;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookCategoryRepository;
import com.example.demo.repository.BookRepository;
import jdk.jfr.Category;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookCategoryRepository bookCategoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getPngPath(), bookDTO.getAgeGroup(), bookDTO.getRating(), bookDTO.getIsbn(), bookDTO.getAmount());
        List<Author> authors = authorRepository.findAllById(bookDTO.getAuthorIds());
        if (!authors.isEmpty()) {
            book.setAuthors(authors);
        }
        BookCategory category = bookCategoryRepository.getCategoryById(bookDTO.getCategoryId());
        if (category != null) {
            book.setCategory(category);
        }
        bookRepository.save(book);
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

            book.setAuthors(Collections.emptyList());
            book.setCategory(null);

            bookRepository.delete(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean editBook(int bookId, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setPngPath(bookDTO.getPngPath());
            book.setAgeGroup(bookDTO.getAgeGroup());
            book.setRating(bookDTO.getRating());
            book.setIsbn(bookDTO.getIsbn());
            book.setAmount(bookDTO.getAmount());

            // get authors from list of ids
//            List<Author> newAuthors = new java.util.ArrayList<>();
//            for (int id : bookDTO.getAuthorIds()) {
//                if (authorRepository.existsById(id)) newAuthors.add(authorRepository.getReferenceById(id));
//            }
//            book.setAuthors(newAuthors);
            List<Author> authors = authorRepository.findAllById(bookDTO.getAuthorIds());
            if (!authors.isEmpty()) {
                book.setAuthors(authors);
            }

            // get catrgory by id
//            BookCategory category = bookCategoryRepository.getReferenceById(bookDTO.getCategoryId());
            BookCategory category = bookCategoryRepository.getCategoryById(bookDTO.getCategoryId());
            book.setCategory(category);
            bookRepository.save(book);

            return true;
        }
        return false;
    }
}
