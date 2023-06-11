package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public void addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable int orderId, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderId, orderDTO);
    }

    @PutMapping("/{orderId}/items")
    public ResponseEntity<String> updateItemInOrder(@PathVariable int orderId, @RequestBody OrderItemDTO orderItemDTO) {
        try{
            orderService.updateItemInOrder(orderId, orderItemDTO);
            return ResponseEntity.ok().body("Order with id " + orderId + " updated succesfully.");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}