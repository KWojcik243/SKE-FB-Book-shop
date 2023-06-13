package com.example.demo.service;

import com.example.demo.dto.BookCategoryDTO;
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
    public BookCategory addCategory(BookCategoryDTO bookCategoryDTO) {
        if(categoryRepository.existsByCategory(bookCategoryDTO.getCategory())){
            throw new RuntimeException("Category " + bookCategoryDTO.getCategory() + " already exists.");
        }
        BookCategory newCategory = new BookCategory(bookCategoryDTO.getCategory());
        return categoryRepository.save(newCategory);
    }

    @Override
    public void updateCategory(int categoryId, BookCategoryDTO bookCategoryDTO) {
        if(categoryRepository.existsByCategory(bookCategoryDTO.getCategory())){
            throw new RuntimeException("Category " + bookCategoryDTO.getCategory() + " already exists.");
        }
        else if (!categoryRepository.existsById(categoryId)){
            throw new RuntimeException("Category with id " + categoryId+ " don't exist in database.");

        }
        else {
            BookCategory bookCategory = categoryRepository.getReferenceById(categoryId);
            bookCategory.setCategory(bookCategoryDTO.getCategory());
            categoryRepository.save(bookCategory);
        }
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
