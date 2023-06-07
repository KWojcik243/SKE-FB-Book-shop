package com.example.demo.service;

import com.example.demo.entity.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    public List<Order> getOrdersByUserId(int id);
    public void addOrder(Timestamp lastStatusUpdate, String status, int paymentType, String address, String city, String postCode);

    public void deleteOrder(int orderId);
    public void updateOrderStatus(int orderId, String status);
}
