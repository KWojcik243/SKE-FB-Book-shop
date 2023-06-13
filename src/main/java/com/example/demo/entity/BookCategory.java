package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
@Table(name = "BookCategories")
@Getter
@Setter
@ToString
public class BookCategory extends RepresentationModel<BookCategory> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Book> books;

    @Column(name = "category")
    private String category;

    public BookCategory() {}

    public BookCategory(String category) {
        this.category = category;
    }
}
