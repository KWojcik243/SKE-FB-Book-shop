package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
    }

    @Override
    public boolean existsByNameAndSurname(String name, String surname) {
        return authorRepository.existsByNameAndSurname(name, surname);
    }

    @Override
    public boolean deleteAuthor(int authorId) {
        if (authorRepository.existsById(authorId)) {

            // Remove author from his books
            List<Book> thisAuthorBooks = authorRepository.getReferenceById(authorId).getBooks();
            Author author = authorRepository.getReferenceById(authorId);
            for(Book book: thisAuthorBooks){
                book.removeAuthor(author);
                bookRepository.save(book);
            }
            // Remove author
            authorRepository.delete(author);
            return true;
        } else return false;
    }

    @Override
    public boolean updateAuthor(int authorId, AuthorDTO authorDTO) {
        if (authorRepository.existsById(authorId)) {
            Author author = authorRepository.getReferenceById(authorId);
            author.setName(authorDTO.getName());
            author.setSurname(authorDTO.getSurname());
            authorRepository.save(author);
            return true;
        } else return false;
    }
}
