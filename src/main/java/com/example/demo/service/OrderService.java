package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrdersByUserId(int id);
    public int addOrder(OrderDTO orderDTO);
    public List<Order> getOrdersByUserEmail(String email);

    public List<Order> getAllOrders();

    public void deleteOrder(int orderId);
    public boolean updateOrder(int orderId, OrderDTO orderItemDTO);
    public boolean updateItemInOrder(int orderId, OrderItemDTO orderItemDTO);

    public Order getOrderById(int orderId);
}
