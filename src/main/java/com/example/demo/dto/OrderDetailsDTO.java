package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailsDTO {
    private int id;
    private List<Integer> bookIds;
    private int orderId;
    private int status;
}
