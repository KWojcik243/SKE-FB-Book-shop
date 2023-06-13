package com.example.demo.service;

import com.example.demo.dto.BookCategoryDTO;
import com.example.demo.entity.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategory> getAllCategories();
    BookCategory getCategoryById(int id);
    BookCategory addCategory(BookCategoryDTO bookCategoryDTO);
    void updateCategory(int categoryId, BookCategoryDTO bookCategoryDTO);
    void deleteCategory(int id);
}
