package com.xpay.userservice.controller;

import com.xpay.userservice.model.User;
import com.xpay.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.xpay.common.constants.ApiEndpoints;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.Users.BASE)
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}