package com.example.demo.repository;

import com.example.demo.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    BookCategory getCategoryById(int id);
    boolean existsByCategory(String category);
}
