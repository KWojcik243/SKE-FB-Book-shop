package com.example.demo.controller;

import com.example.demo.dto.BookCategoryDTO;
import com.example.demo.entity.BookCategory;
import com.example.demo.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class BookCategoryController {
    private final BookCategoryService categoryService;

    @Autowired
    public BookCategoryController(BookCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<BookCategory>>> getAllCategories() {
        List<BookCategory> categories = categoryService.getAllCategories();

        List<EntityModel<BookCategory>> categoryModels = categories.stream()
                .map(category -> {
                    String uri = "/categories/" + category.getId();
                    Link selfLink = Link.of(getBaseUrl() + uri).withSelfRel();
                    Link deleteLink = Link.of(getBaseUrl() + uri).withRel("DELETE");
                    Link putLink = Link.of(getBaseUrl() + uri).withRel("PUT");

                    return EntityModel.of(category, selfLink, deleteLink, putLink);
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<BookCategory>> categoriesResource = CollectionModel.of(categoryModels);
        categoriesResource.add(Link.of(getBaseUrl() + "/categories").withSelfRel());

        return ResponseEntity.ok(categoriesResource);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookCategory>> getCategoryById(@PathVariable int id) {
        BookCategory category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        String uri = "/categories/" + id;
        EntityModel<BookCategory> categoryResource = EntityModel.of(category,
                Link.of(getBaseUrl() + uri).withSelfRel(),
                Link.of(getBaseUrl() + uri).withRel("PUT"),
                Link.of(getBaseUrl() + uri).withRel("DELETE")
        );

        return ResponseEntity.ok(categoryResource);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody BookCategoryDTO bookCategoryDTO) {
        try {
            BookCategory newCategory = categoryService.addCategory(bookCategoryDTO);
            return ResponseEntity.ok(newCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editCategory(@PathVariable int id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        try {
            categoryService.updateCategory(id, bookCategoryDTO);
            return ResponseEntity.ok("Category with ID " + id + " has been updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category with id " + id + " deleted.");
    }

    private String getBaseUrl() {
        String requestUrl = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        return requestUrl.replaceFirst("/categories.*", "");
    }
}
