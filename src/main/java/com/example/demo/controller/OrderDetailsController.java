package com.example.demo.controller;

import com.example.demo.entity.OrderDetails;
import com.example.demo.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping
    public ResponseEntity<String> addOrderDetails(@RequestParam int status,
                                                  @RequestParam int orderId,
                                                  @RequestParam List<Integer> bookIds) {
        for (Integer bookId : bookIds) {
            boolean added = orderDetailsService.addBookToOrderDetailsStatus(orderId, bookId);
            if (!added) {
                return ResponseEntity.badRequest().body("The book with ID " + bookId + " is out of stock.");
            }
        }
        orderDetailsService.addOrderDetails(status, orderId);
        return ResponseEntity.ok("Order details added successfully.");
    }

    @PostMapping("/add-book-to-order")
    public ResponseEntity<String> addBookToOrderDetails(@RequestParam int orderDetailsId,
                                                        @RequestParam int bookId) {
        boolean added = orderDetailsService.addBookToOrderDetailsStatus(orderDetailsId, bookId);
        if (!added) {
            return ResponseEntity.badRequest().body("The book with ID " + bookId + " is out of stock.");
        } else {
            return ResponseEntity.ok("Book added successfully.");
        }
    }

    @DeleteMapping("/delete-book-from-order")
    public ResponseEntity<String> deleteBookFromOrderDetails(@RequestParam int orderDetailsId,
                                                             @RequestParam int bookId) {
        orderDetailsService.removeBookFromOrderDetails(orderDetailsId, bookId);
        return ResponseEntity.ok("Book deleted successfully from order.");
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateOrderDetailsStatus(@RequestParam int orderDetailsId,
                                                           @RequestParam int status) {
        orderDetailsService.updateOrderDetailsStatus(orderDetailsId, status);
        return ResponseEntity.ok("Order details status updated successfully.");
    }

    @DeleteMapping("/delete/{orderDetailsId}")
    public ResponseEntity<String> deleteOrderDetails(@PathVariable int orderDetailsId) {
        orderDetailsService.deleteOrderDetails(orderDetailsId);
        return ResponseEntity.ok("Order details deleted successfully.");
    }

    @GetMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable int orderDetailsId) {
        OrderDetails orderDetails = orderDetailsService.getOrderDetails(orderDetailsId);
        if (orderDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
    }

}
