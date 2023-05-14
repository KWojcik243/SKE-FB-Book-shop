package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PaymentsInfo")
@Getter
@Setter
@ToString
public class PaymentsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "PaymentsInfo", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "card_nb")
    private String cardNumber;

    @Column(name = "phone_nb")
    private String phoneNumber;

    public PaymentsInfo() {}

    public PaymentsInfo(String firstName, String lastName, String address, String postCode, String cardNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
    }
}
