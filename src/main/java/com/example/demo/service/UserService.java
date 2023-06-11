package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface UserService {
    public void addUser(UserDTO userDTO);

    public void removeUser(String email);

    public void changeUserPassword(String email, String previousPassword, String newPassword);

    public void grantAdmin(String email);
    public void revokeAdmin(String email);
}
