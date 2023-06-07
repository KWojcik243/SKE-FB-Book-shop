package com.example.demo.service;

public interface UserService {
    public void addUser(String name, String surname, String email, String password);

    public void removeUser(int id);

    public void changeUserPassword(int id, String previousPassword, String newPassword);
}
