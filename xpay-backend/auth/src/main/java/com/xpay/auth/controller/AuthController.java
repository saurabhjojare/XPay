package com.xpay.auth.controller;

import com.xpay.auth.constant.Constants;
import com.xpay.auth.dto.AuthRequest;
import com.xpay.auth.dto.AuthResponse;
import com.xpay.auth.dto.UserRegistrationRequest;
import com.xpay.auth.service.authentication.AuthenticationService;
import com.xpay.auth.service.users.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

// Controller for handling authentication requests
@RestController
@RequestMapping(Constants.API + Constants.AUTH)
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthService authService;

    // Constructor for dependency injection
    public AuthController(AuthenticationService authenticationService, AuthService authService) {
        this.authenticationService = authenticationService;
        this.authService = authService;
    }

    // Handles login requests
    @PostMapping(Constants.LOGIN)
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    // Handles signup requests and returns HTTP 201 Created
    @PostMapping(Constants.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRegistrationRequest registrationRequest) {
        authService.registerUser(registrationRequest);
    }
}
