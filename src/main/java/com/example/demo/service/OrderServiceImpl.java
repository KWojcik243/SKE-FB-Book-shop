package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByUserId(@RequestParam int id) {
        return orderRepository.findByUserId(id);
    }

    public void addOrder(@RequestParam Timestamp lastStatusUpdate,
                         @RequestParam String status,
                         @RequestParam int paymentType,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam String postCode) {
        Order order = new Order(lastStatusUpdate, status, paymentType, address, city, postCode);
        orderRepository.save(order);
    }
}
