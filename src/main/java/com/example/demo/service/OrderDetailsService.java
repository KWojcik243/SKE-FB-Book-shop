package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface OrderDetailsService {
    public void addOrderDetails(@RequestParam int status,
                                @RequestParam int orderId);

    public void updateOrderDetailsStatus(@RequestParam int orderDetailsId,
                                         @RequestParam int orderStatus);

    public Boolean addBookToOrderDetailsStatus(@RequestParam int orderDetailsId,
                                               @RequestParam int bookId);

    public void removeBookFromOrderDetails(@RequestParam int orderDetailsId,
                                           @RequestParam int bookId);

}
