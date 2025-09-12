package com.xpay.auth.controller;

import com.xpay.auth.constant.ApiEndpoints;
import com.xpay.auth.dto.AuthRequestDTO;
import com.xpay.auth.dto.AuthResponseDTO;
import com.xpay.auth.dto.UserRequestDTO;
import com.xpay.auth.service.authentication.AuthenticationService;
import com.xpay.auth.service.users.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

// Controller for handling authentication requests
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.API + ApiEndpoints.AUTH)
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthService authService;

    // Handles login requests
    @PostMapping(ApiEndpoints.LOGIN)
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    // Handles signup requests and returns HTTP code
    @PostMapping(ApiEndpoints.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRequestDTO registrationRequest) {
        authService.registerUser(registrationRequest);
    }

    // Handles delete requests
    @DeleteMapping(ApiEndpoints.DELETE)
    public void deleteUserByUserId(@PathVariable("userId") UUID userId) {
        authService.deleteUserById(userId);
    }
}
