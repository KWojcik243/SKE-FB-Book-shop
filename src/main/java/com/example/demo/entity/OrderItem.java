package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
@ToString
public class OrderItem extends RepresentationModel<OrderItem> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @JsonProperty("bookId")
    public Integer getBookId() {
        return book.getId();
    }
}

// todo naprawic -> nie dziala getter /orders/orderid