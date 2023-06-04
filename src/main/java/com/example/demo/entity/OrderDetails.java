package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OrdersDetails")
@Getter
@Setter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_details_books",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status")
    private int status;

    public OrderDetails() {
    }

    public OrderDetails(int status) {
        this.status = status;
        this.books = new ArrayList<>();
    }

    public void addBookToOrderDetails(Book book) {
        books.add(book);
    }

    public void removeBookFromOrderDetails(Book book) {
        if (books != null) {
            books.remove(book);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
