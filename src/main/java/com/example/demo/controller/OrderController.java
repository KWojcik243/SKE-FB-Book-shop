package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrdersByUserId(@PathVariable int id) {
        return orderService.getOrdersByUserId(id);
    }

    @PostMapping
    public void addOrder(@RequestParam Timestamp lastStatusUpdate,
                         @RequestParam String status,
                         @RequestParam int paymentType,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam String postCode) {
        orderService.addOrder(lastStatusUpdate, status, paymentType, address, city, postCode);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public void updateOrderStatus(@PathVariable int orderId, @RequestParam String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
    }
}
