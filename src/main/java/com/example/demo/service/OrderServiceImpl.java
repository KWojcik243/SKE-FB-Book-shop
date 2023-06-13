package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderItemRepository;
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
    private final BookRepository bookRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<Order> getOrdersByUserId(int id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> getOrdersByUserEmail(String email) {
        return orderRepository.findByUserEmail(email);
    }

    @Override
    public int addOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getLastStatusUpdate(), orderDTO.getStatus(), orderDTO.getPaymentType(),
                orderDTO.getAddress(), orderDTO.getCity(), orderDTO.getPostCode());
        orderRepository.save(order);
        return order.getId();
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
            Optional<User> optionalUser = userRepository.findByEmail(orderDTO.getUserEmail());
            if (optionalUser.isPresent()) {
                order.setUser(optionalUser.get());
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


    @Override
    public boolean updateItemInOrder(int orderId, OrderItemDTO orderItemDTO) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order with id " + orderId + " doesn't exist.");
        }
        Order order = orderRepository.getReferenceById(orderId);

        if (!bookRepository.existsById(orderItemDTO.getBookId())){
            throw new RuntimeException("Book with id " + orderItemDTO.getBookId() + " doesn't exist.");
        }
        Book book = bookRepository.getReferenceById(orderItemDTO.getBookId());

        // Check if book is in order - if yes, just update info about difference in amount
        int previousQuantity = 0;
        for (OrderItem existingOrderItem : order.getOrderItems()) {
            if (existingOrderItem.getBook().equals(book)) {
                previousQuantity = existingOrderItem.getQuantity();
                break;
            }
        }
        int newQuantity = orderItemDTO.getQuantity();
        int quantityDifference = newQuantity - previousQuantity;

        // Check if there are enough books in stock
        boolean possibleToOrder = book.updateAmountBy(quantityDifference);
        if (!possibleToOrder) {
            throw new RuntimeException("Cannot order " + quantityDifference + " additional books of " + book.getTitle() +
                    " because there are only " + book.getAmount() + " available.");
        }

        // Update info in order
        boolean foundExistingOrderItem = false;
        for (OrderItem existingOrderItem : order.getOrderItems()) {
            if (existingOrderItem.getBook().equals(book)) {
                existingOrderItem.setQuantity(newQuantity);
                foundExistingOrderItem = true;
                orderItemRepository.save(existingOrderItem);
                break;
            }
        }

        if (!foundExistingOrderItem) {
            OrderItem orderItem = new OrderItem(book, newQuantity);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            orderItemRepository.save(orderItem);
        }

        orderRepository.save(order);
        bookRepository.save(book);

        return true;
    }


    @Override
    public Order getOrderById(int orderId) {
        if (!orderRepository.existsById(orderId)) return null;
        return orderRepository.getReferenceById(orderId);
    }
}
