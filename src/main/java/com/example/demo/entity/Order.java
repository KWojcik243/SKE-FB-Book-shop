package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "Orders", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> users;

    @Column(name = "last_status_update")
    private Timestamp lastStatusUpdate;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_type")
    private int paymentType;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code")
    private String postCode;

    public Order() {}

    public Order(Timestamp lastStatusUpdate, String status, int paymentType, String address, String city, String postCode) {
        this.lastStatusUpdate = lastStatusUpdate;
        this.status = status;
        this.paymentType = paymentType;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
    }
}
