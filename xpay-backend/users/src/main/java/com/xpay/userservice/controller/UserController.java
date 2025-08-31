package com.xpay.userservice.controller;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.service.UserService;
import com.xpay.userservice.constants.ApiEndpoints;

import com.xpay.userservice.utility.PaginationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.Users.BASE_USERS)
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

    @GetMapping(ApiEndpoints.Users.GET_BY_ID)
    public UserResponseDTO getUserById(@PathVariable("id") String id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }

    @PostMapping()
    public Boolean createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            return userService.createUser(userRequestDTO);
        } catch (Exception e) {
            log.error("Error creating user with data: {}", userRequestDTO, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }

    @DeleteMapping(ApiEndpoints.Users.GET_BY_ID)
    public Boolean deleteUserById(@PathVariable("id") String id) {
        try {
            return userService.deleteUserById(id);
        } catch (Exception e) {
            log.error("Error deleting user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
        }
    }

    @PatchMapping(ApiEndpoints.Users.GET_BY_ID)
    public Boolean updateUserById(@PathVariable("id") String id, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            userService.updateUserById(id, userRequestDTO);
            return true;
        } catch (Exception e) {
            log.error("Error partially updating user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user", e);
        }
    }
}