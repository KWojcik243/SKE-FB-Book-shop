package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class OrderDTO {
    private int userId;

    @JsonIgnore
    private Timestamp lastStatusUpdate;

    private String status;
    private int paymentType;
    private String address;
    private String city;
    private String postCode;

    public OrderDTO(int userId, String status, int paymentType, String address, String city, String postCode){
        this.userId = userId;
        this.status = status;
        this.paymentType = paymentType;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        lastStatusUpdate = Timestamp.from(Instant.now());
    }
}
