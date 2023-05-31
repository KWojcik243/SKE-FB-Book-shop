package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    public void addUser(@RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam String email,
                        @RequestParam String password);

    public void removeUser(@RequestParam int id);

    public void changeUserPassword(@RequestParam int id,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword);
}
