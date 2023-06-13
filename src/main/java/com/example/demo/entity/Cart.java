package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Carts")
@Getter
@Setter
public class Cart extends RepresentationModel<Cart> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @JsonProperty("userEmail")
    public String userEmail(){
        return user.getEmail();
    }

    @ElementCollection
    @CollectionTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id")
    )
    @MapKeyColumn(name = "book_id")
    @Column(name = "quantity")
    private Map<Integer, Integer> cartItems;


    public Cart() {
    }

    public Cart(User user){
        this.user = user;
    }

}