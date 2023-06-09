package com.example.demo.controller;

import com.example.demo.dto.BookCategoryDTO;
import com.example.demo.entity.BookCategory;
import com.example.demo.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class BookCategoryController {
    private final BookCategoryService categoryService;

    @Autowired
    public BookCategoryController(BookCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<BookCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategory> getCategoryById(@PathVariable int id) {
        BookCategory category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<BookCategory> addCategory(@RequestBody BookCategoryDTO bookCategoryDTO) {
        BookCategory newCategory = categoryService.addCategory(bookCategoryDTO);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editCategory(@PathVariable int id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        boolean updated = categoryService.updateCategory(id, bookCategoryDTO);
        if (updated) {
            return ResponseEntity.ok("Category with ID " + id + " has been updated.");
        } else {
            return ResponseEntity.badRequest().body("Unable to update category with ID " + id + ".");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category with id " + id + " deleted.");
    }
}
