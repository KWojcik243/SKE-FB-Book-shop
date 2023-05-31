package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/ordersByUser")
    public List<Order> getOrdersByUserId(@RequestParam int id){
        return orderService.getOrdersByUserId(id);
    }

    @PostMapping("/order")
    public void addOrder(@RequestParam Timestamp lastStatusUpdate,
                         @RequestParam String status,
                         @RequestParam int paymentType,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam String postCode){
        this.orderService.addOrder(lastStatusUpdate, status, paymentType, address, city, postCode);
    }

}
