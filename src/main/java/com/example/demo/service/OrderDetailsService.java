package com.example.demo.service;

import com.example.demo.entity.OrderDetails;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderDetailsService {
    public void addOrderDetails(int status, int orderId);

    public void updateOrderDetailsStatus(int orderDetailsId, int orderStatus);

    public Boolean addBookToOrderDetailsStatus(int orderDetailsId, int bookId);

    public void removeBookFromOrderDetails(int orderDetailsId, int bookId);

    public void deleteOrderDetails(int id);
    OrderDetails getOrderDetails(int orderDetailsId);

}
