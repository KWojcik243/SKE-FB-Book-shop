package com.example.demo.service;

import com.example.demo.entity.Order;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    public List<Order> getOrdersByUserId(@RequestParam int id);
    public void addOrder(@RequestParam Timestamp lastStatusUpdate,
                         @RequestParam String status,
                         @RequestParam int paymentType,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam String postCode);
}
