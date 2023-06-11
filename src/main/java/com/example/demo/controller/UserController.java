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

    @Secured("ROLE_ADMIN")
    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{email}")
    public void changeUserPassword(@PathVariable String email,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword) {
        userService.changeUserPassword(email, previousPassword, newPassword);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{email}")
    public void removeUser(@PathVariable String email) {
        userService.removeUser(email);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/grant-admin/{email}")
    public void grantAdmin(@PathVariable String email) {
        userService.grantAdmin(email);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/revoke-admin/{email}")
    public void revokeAdmin(@PathVariable String email) {
        userService.revokeAdmin(email);
    }

}
