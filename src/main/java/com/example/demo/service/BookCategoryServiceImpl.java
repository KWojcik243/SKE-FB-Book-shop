package com.example.demo.service;

import com.example.demo.entity.BookCategory;
import com.example.demo.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository categoryRepository;

    @Autowired
    public BookCategoryServiceImpl(BookCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<BookCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public BookCategory getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElse(null);
    }

    @Override
    public BookCategory addCategory(String category) {
        BookCategory newCategory = new BookCategory(category);
        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
