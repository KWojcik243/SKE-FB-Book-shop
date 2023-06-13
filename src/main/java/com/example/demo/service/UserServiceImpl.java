package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword(), Role.USER);
        Cart cart = new Cart(user);
        user.setCart(cart);
        userRepository.save(user);
        cartRepository.save(cart);
    }

    @Override
    public void changeUserPassword(String email, String previousPassword, String newPassword) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(previousPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void removeUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            cartRepository.delete(user.getCart());
            userRepository.delete(user);
        }
    }

    @Override
    public void grantAdmin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            user.get().grantAdminPrivileges();
        }
        else {
            throw new RuntimeException("User with email " + email + " does not exist.");
        }
    }

    @Override
    public void revokeAdmin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            user.get().revokeAdminPrivileges();
        }
        else {
            throw new RuntimeException("User with email " + email + " does not exist.");
        }
    }
}
