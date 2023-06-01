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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @Column(name = "title")
    private String title;

    @Column(name = "png_path")
    private String pngPath;

    @Column(name = "age_group")
    private int ageGroup;

    @Column(name = "rating")
    private float rating;

    @Column(name = "isbn")
    private long isbn;

    @Column(name = "amount")
    private int amount;

    public Book() {}

    public Book(String title, String pngPath, int ageGroup, float rating, long isbn, int amount) {
        this.title = title;
        this.pngPath = pngPath;
        this.ageGroup = ageGroup;
        this.rating = rating;
        this.isbn = isbn;
        this.amount = amount;
    }

    public void updateAmountBy(int nAmount){
        this.amount += nAmount;
    }
}
