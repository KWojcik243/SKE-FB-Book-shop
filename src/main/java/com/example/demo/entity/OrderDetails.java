package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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

    @OneToOne(mappedBy = "OrdersDetails", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private Book books;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private Order orders;

    @Column(name = "status")
    private int status;

    public OrderDetails() {}

    public OrderDetails(int status) {
        this.status = status;
    }
}
