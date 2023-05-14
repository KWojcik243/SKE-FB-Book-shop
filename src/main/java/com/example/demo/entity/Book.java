package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "Books", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Author> authors;

    @Column(name = "name")
    private String name;

    @Column(name = "age_group")
    private int ageGroup;

    @Column(name = "rating")
    private double rating;

    @Column(name = "isbn")
    private long isbn;

    @Column(name = "amount")
    private int amount;

    public Book() {}

    public Book(String name, int ageGroup, double rating, long isbn, int amount) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.rating = rating;
        this.isbn = isbn;
        this.amount = amount;
    }
}
