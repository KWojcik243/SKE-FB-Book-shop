package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    public OrderDetailsRepository orderDetailsRepository;
    public BookRepository bookRepository;
    public OrderRepository orderRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository,
                                   BookRepository bookRepository,
                                   OrderRepository orderRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public Boolean addBookToOrderDetailsStatus(int orderDetailsId, int bookId) {
        OrderDetails orderDetails = orderDetailsRepository.getReferenceById(orderDetailsId);
        Book book = bookRepository.getReferenceById(bookId);

        if (book.getAmount() < 1) {
            return false;
        }

        orderDetails.addBookToOrderDetails(book);
        book.updateAmountBy(-1);
        return true;

    }

    @Override
    public void removeBookFromOrderDetails(int orderDetailsId, int bookId) {
        OrderDetails orderDetails = orderDetailsRepository.getReferenceById(orderDetailsId);
        Book book = bookRepository.getReferenceById(bookId);
        orderDetails.removeBookFromOrderDetails(book);
        book.updateAmountBy(1);
    }

    @Override
    public void updateOrderDetailsStatus(int orderDetailsId, int orderStatus) {
        OrderDetails orderDetails = orderDetailsRepository.getReferenceById(orderDetailsId);
        orderDetails.setStatus(orderStatus);
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void addOrderDetails(int status, int orderId) {
        OrderDetails orderDetails = new OrderDetails(status);

        // set order
        Order order = orderRepository.getReferenceById(orderId);
        orderDetails.setOrder(order);

        this.orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void deleteOrderDetails(int id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order details not found with id: " + id));

        List<Book> books = orderDetails.getBooks();
        for (Book book : books) {
            book.updateAmountBy(1);
        }

        orderDetailsRepository.delete(orderDetails);
    }

    @Override
    public OrderDetails getOrderDetails(int orderDetailsId) {
        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findById(orderDetailsId);
        return optionalOrderDetails.orElse(null);
    }
}
