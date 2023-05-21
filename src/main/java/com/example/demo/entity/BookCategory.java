package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BookCategories")
@Getter
@Setter
@ToString
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "Orders", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Book> books;

    @Column(name = "category")
    private String category;

    public BookCategory() {}

    public BookCategory(String category) {
        this.category = category;
    }
}
