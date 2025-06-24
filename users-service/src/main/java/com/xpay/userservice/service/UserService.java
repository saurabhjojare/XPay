package com.xpay.userservice.service;

import com.xpay.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUserById(String id);
    Optional<User> getUserById(String id);
}
