package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
@ToString
@JsonIgnoreProperties("cart")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private BookCategory category;

    public Book() {}

    public Book(String title, String pngPath, int ageGroup, float rating, long isbn, int amount) {
        this.title = title;
        this.pngPath = pngPath;
        this.ageGroup = ageGroup;
        this.rating = rating;
        this.isbn = isbn;
        this.amount = amount;
    }

    public boolean updateAmountBy(int nAmount){
        if(this.amount + nAmount < 0){
            return false;
        }
        this.amount += nAmount;
        return true;
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
    }

    @JsonProperty("category")
    public String getCategoryName() {
        if (category != null) {
            return category.getCategory();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pngPath='" + pngPath + '\'' +
                ", ageGroup=" + ageGroup +
                ", rating=" + rating +
                ", isbn=" + isbn +
                ", amount=" + amount +
                '}';
    }
}
