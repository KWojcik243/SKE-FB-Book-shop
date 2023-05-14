package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
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
    private List<Author> users;

    @Column(name = "last_status_update")
    private Date lastStatusUpdate;

    @Column(name = "status")
    private String status;

    public Order() {}

    public Order(Date lastStatusUpdate, String status) {
        this.lastStatusUpdate = lastStatusUpdate;
        this.status = status;
    }
}
