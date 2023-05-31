package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String name, String surname, String email, String password) {
        User user = new User(name, surname, email, password);
        userRepository.save(user);
    }

    @Override
    public void changeUserPassword(int id, String previousPassword, String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null && user.getPassword().equals(previousPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void removeUser(int id) {
        userRepository.deleteById(id);
    }
}
