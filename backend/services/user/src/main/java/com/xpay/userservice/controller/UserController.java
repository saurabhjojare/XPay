package com.xpay.userservice.controller;

import com.xpay.userservice.dto.response.UserResponseDTO;
import com.xpay.userservice.service.interfaces.UserService;
import com.xpay.userservice.constant.Routes;
import com.xpay.userservice.utility.PaginationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(Routes.BASE_API + Routes.User.BASE_USERS)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    public PaginationUtil.PaginatedResponse<UserResponseDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            return userService.getAllUsers(page, size, sortBy, sortDir);
        } catch (Exception e) {
            log.error("Error fetching users", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching users", e);
        }
    }

    @GetMapping(Routes.User.GET_BY_ID)
    public UserResponseDTO getUserById(@PathVariable("userId") UUID userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }
}