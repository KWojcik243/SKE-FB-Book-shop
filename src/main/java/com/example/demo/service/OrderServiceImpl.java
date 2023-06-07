package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public void addOrder(Timestamp lastStatusUpdate, String status, int paymentType, String address, String city, String postCode) {
        Order order = new Order(lastStatusUpdate, status, paymentType, address, city, postCode);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }
}
