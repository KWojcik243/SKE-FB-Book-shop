package com.example.demo.controller;

import com.example.demo.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailsController {
    OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping("/addOrderDetails")
    public ResponseEntity<String> addOrderDetails(@RequestParam int status,
                                                  @RequestParam int orderId,
                                                  List<Integer> bookIds) {
        for (Integer bookId : bookIds) {
            boolean added = orderDetailsService.addBookToOrderDetailsStatus(orderId, bookId);
            if (!added) {
                return ResponseEntity.badRequest().body("The book with ID " + bookId + " is out of stock.");
            }
        }
        orderDetailsService.addOrderDetails(status, orderId);
        return ResponseEntity.ok("Order details added successfully.");
    }

    @PostMapping("/addBookToOrderDetails")
    public ResponseEntity<String> addBookToOrderDetails(@RequestParam int orderDetailsId,
                                                        @RequestParam int bookId) {
        boolean added = orderDetailsService.addBookToOrderDetailsStatus(orderDetailsId, bookId);
        if (!added) {
            return ResponseEntity.badRequest().body("The book with ID " + bookId + " is out of stock.");
        } else {
            return ResponseEntity.ok("Book added successfully.");
        }
    }

    @PostMapping("/deleteBookFromOrderDetails")
    public ResponseEntity<String> deleteBookFromOrderDetails(@RequestParam int orderDetailsId,
                                                             @RequestParam int bookId) {
        orderDetailsService.removeBookFromOrderDetails(orderDetailsId, bookId);
        return ResponseEntity.ok("Book deleted successfully from order.");
    }

    @PostMapping("/updateOrderDetailsStatus")
    public ResponseEntity<String> updateOrderDetailsStatus(@RequestParam int orderDetailsId,
                                                           @RequestParam int status) {
        this.orderDetailsService.updateOrderDetailsStatus(orderDetailsId, status);
        return ResponseEntity.ok("Order details status updated succesfully.");
    }
}
