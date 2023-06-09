package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public void addOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getLastStatusUpdate(), orderDTO.getStatus(), orderDTO.getPaymentType(),
                orderDTO.getAddress(), orderDTO.getCity(), orderDTO.getPostCode());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public boolean updateOrder(int orderId, OrderDTO orderDTO) {
        if (orderRepository.existsById(orderId)) {
            Order order = orderRepository.getReferenceById(orderId);
            order.setStatus(orderDTO.getStatus());
            order.setCity(orderDTO.getCity());
            order.setAddress(orderDTO.getAddress());
            order.setPaymentType(orderDTO.getPaymentType());

            if (userRepository.existsById(orderDTO.getUserId())) {
                order.setUser(userRepository.getReferenceById(orderDTO.getUserId()));
            }

            order.setPostCode(orderDTO.getPostCode());
            order.setLastStatusUpdate(orderDTO.getLastStatusUpdate());
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
