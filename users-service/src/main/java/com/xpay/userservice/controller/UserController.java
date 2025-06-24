package com.xpay.userservice.controller;

import com.xpay.userservice.model.User;
import com.xpay.userservice.repository.UserRepository;
import com.xpay.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xpay.common.constants.ApiEndpoints;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.Users.BASE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(ApiEndpoints.Users.GET_BY_ID)
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @DeleteMapping(ApiEndpoints.Users.GET_BY_ID)
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
    }

}