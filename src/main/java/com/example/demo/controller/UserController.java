package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @PutMapping("/{email}")
    public void changeUserPassword(@PathVariable String email,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword) {
        userService.changeUserPassword(email, previousPassword, newPassword);
    }

    @DeleteMapping("/{email}")
    public void removeUser(@PathVariable String email) {
        userService.removeUser(email);
    }

    @PutMapping("/grant-admin/{email}")
    public void grantAdmin(@PathVariable String email) {
        userService.grantAdmin(email);
    }

    @PutMapping("/revoke-admin/{email}")
    public void revokeAdmin(@PathVariable String email) {
        userService.revokeAdmin(email);
    }

}
