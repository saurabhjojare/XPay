package com.xpay.auth.controller;

import com.xpay.auth.constant.Constants;
import com.xpay.auth.dto.AuthRequest;
import com.xpay.auth.dto.AuthResponse;
import com.xpay.auth.service.authentication.AuthenticationService;
import com.xpay.auth.service.users.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

// Controller for handling authentication requests
@RestController
@RequestMapping(Constants.AUTH)
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthService authService;

    public AuthController(AuthenticationService authenticationService, AuthService authService) {
        this.authenticationService = authenticationService;
        this.authService = authService;
    }

    @PostMapping(Constants.LOGIN)
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    @PostMapping(Constants.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody AuthRequest authRequest) {
        UUID userID = UUID.randomUUID();
        authService.registerUser(userID, authRequest.getUsername(), authRequest.getPassword());
    }
}
