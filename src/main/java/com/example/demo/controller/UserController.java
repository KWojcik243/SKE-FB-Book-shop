package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam String email,
                        @RequestParam String password) {
        userService.addUser(name, surname, email, password);
    }

    @PutMapping("/change-password")
    public void changeUserPassword(@RequestParam int id,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword) {
        userService.changeUserPassword(id, previousPassword, newPassword);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable int id) {
        userService.removeUser(id);
    }
}
