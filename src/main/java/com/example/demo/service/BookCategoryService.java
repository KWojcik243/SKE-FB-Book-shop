package com.example.demo.service;

import com.example.demo.entity.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategory> getAllCategories();
    BookCategory getCategoryById(int id);
    BookCategory addCategory(String category);
    void deleteCategory(int id);
}
