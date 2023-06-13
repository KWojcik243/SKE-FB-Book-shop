package com.example.demo.service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Cart;

public interface CartService {
    public void updateCartByBook(String userEmail, OrderItemDTO orderItemDTO);
    public Cart getCartByEmail(String email);
}
