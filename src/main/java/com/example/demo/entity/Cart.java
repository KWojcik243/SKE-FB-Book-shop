package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Carts")
@Getter
@Setter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "cart_book",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
//
//    private List<Book> books;

    @ElementCollection
    @CollectionTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id")
    )
    @MapKeyJoinColumn(name = "book_id")
    @Column(name = "quantity")
    private Map<Book, Integer> cartItems;


    public Cart() {
    }

    public Cart(User user){
        this.user = user;
    }

}