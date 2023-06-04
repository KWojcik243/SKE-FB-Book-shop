package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam String email,
                        @RequestParam String password) {
        userService.addUser(name, surname, email, password);
    }

    @PostMapping("/changePassword")
    public void changeUserPassword(@RequestParam int id,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword) {
        userService.changeUserPassword(id, previousPassword, newPassword);
    }

    @PostMapping("removeUser")
    public void removeUser(@RequestParam int id) {
        this.userService.removeUser(id);
    }
}
