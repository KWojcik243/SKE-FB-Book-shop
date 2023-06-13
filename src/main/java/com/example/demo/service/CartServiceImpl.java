package com.example.demo.service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;

    public CartServiceImpl(BookRepository bookRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public void updateCartByBook(String userEmail, OrderItemDTO orderItemDTO) {
        if(!bookRepository.existsById(orderItemDTO.getBookId())){
            throw new RuntimeException("No book with id " + orderItemDTO.getBookId() + " in magazine");
        }

        if(userRepository.findByEmail(userEmail).isEmpty()) {
            throw new RuntimeException("No user with email " + userEmail);
        }

        Book book = bookRepository.getReferenceById(orderItemDTO.getBookId());
        Cart cart = userRepository.findByEmail(userEmail).get().getCart();

        // check if book is in cart
        int quantityInStock = 0;
        for (Map.Entry<Integer, Integer> entry : cart.getCartItems().entrySet()) {
            if (bookRepository.getReferenceById(entry.getKey()).equals(book)) {
                quantityInStock = entry.getValue();
                break;
            }
        }
        // check if delta is greather than n in magazine
        int quantityDelta = orderItemDTO.getQuantity() - quantityInStock;
        if(quantityDelta > book.getAmount()){
            throw new RuntimeException("Not enough book in magazine");
        }
        else {
            cart.getCartItems().put(book.getId(), orderItemDTO.getQuantity());
            book.updateAmountBy(-quantityDelta);
            cartRepository.save(cart);
            bookRepository.save(book);
        }
    }

    @Override
    public Cart getCartByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) return user.get().getCart();
        else return null;
    }
}
