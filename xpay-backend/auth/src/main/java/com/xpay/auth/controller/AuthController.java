package com.xpay.auth.controller;

import com.xpay.auth.constant.Constants;
import com.xpay.auth.dto.AuthRequestDTO;
import com.xpay.auth.dto.AuthResponseDTO;
import com.xpay.auth.dto.UserRequestDTO;
import com.xpay.auth.service.authentication.AuthenticationService;
import com.xpay.auth.service.users.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    // Handles signup requests and returns HTTP 201 Created
    @PostMapping(Constants.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRequestDTO registrationRequest) {
        authService.registerUser(registrationRequest);
    }
}
