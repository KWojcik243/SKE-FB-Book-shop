package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://127.0.0.1:5173"})
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Order>>> getOrders() {
        List<Order> orders = orderService.getAllOrders();

        List<EntityModel<Order>> orderModels = orders.stream()
                .map(order -> EntityModel.of(order,
                        Link.of(getBaseUrl() + "/orders/" + order.getId()).withSelfRel(),
                        Link.of(getBaseUrl() + "/orders/" + order.getId() + "/items").withRel("items: PUT"),
                        Link.of(getBaseUrl() + "/orders/" + order.getId()).withRel("PUT"),
                        Link.of(getBaseUrl() + "/orders/" + order.getId()).withRel("DELETE")))


                .collect(Collectors.toList());

        CollectionModel<EntityModel<Order>> ordersResource = CollectionModel.of(orderModels);
        ordersResource.add(Link.of(getBaseUrl()+ "/orders").withSelfRel());

        return ResponseEntity.ok(ordersResource);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<EntityModel<Order>> getOrder(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }


        EntityModel<Order> orderResource = EntityModel.of(order,
                Link.of(getBaseUrl() + "/orders/" + order.getId()).withSelfRel(),
                Link.of(getBaseUrl() + "/orders/" + order.getId() + "/items").withRel("items: PUT"),
                Link.of(getBaseUrl() + "/orders/" + order.getId()).withRel("PUT"),
                Link.of(getBaseUrl() + "/orders/" + order.getId()).withRel("DELETE"));

        return ResponseEntity.ok(orderResource);
    }


    @PostMapping
    public ResponseEntity<Integer> addOrder(@RequestBody OrderDTO orderDTO) {
        int orderId = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(orderId);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable int orderId, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}/items")
    public ResponseEntity<String> updateItemInOrder(@PathVariable int orderId, @RequestBody OrderItemDTO orderItemDTO) {
        try {
            orderService.updateItemInOrder(orderId, orderItemDTO);
            return ResponseEntity.ok().body("Order with id " + orderId + " updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userEmail}")
    public ResponseEntity<CollectionModel<EntityModel<Order>>> getUserOrders(@PathVariable String userEmail) {
        List<Order> orders = orderService.getOrdersByUserEmail(userEmail);

        List<EntityModel<Order>> orderModels = orders.stream()
                .map(order -> EntityModel.of(order,
                        Link.of("/orders/" + order.getId()).withSelfRel(),
                        Link.of("/orders/" + order.getId() + "/items").withRel("items")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Order>> ordersResource = CollectionModel.of(orderModels);
        ordersResource.add(Link.of("/orders").withSelfRel());

        return ResponseEntity.ok(ordersResource);
    }
    private String getBaseUrl() {
        String requestUrl = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
        return requestUrl.replaceFirst("/orders.*", "");
    }

}
