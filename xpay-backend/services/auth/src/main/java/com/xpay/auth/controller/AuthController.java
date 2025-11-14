package com.xpay.auth.controller;

import com.xpay.auth.constant.Routes;
import com.xpay.auth.dto.request.AuthRequestDTO;
import com.xpay.auth.dto.response.AuthResponseDTO;
import com.xpay.auth.dto.request.UserRequestDTO;
import com.xpay.auth.service.authentication.AuthenticationService;
import com.xpay.auth.service.users.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.UUID;

// Controller for handling authentication requests
@RequiredArgsConstructor
@RestController
@RequestMapping(Routes.API + Routes.AUTH)
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthService authService;

    // Handles login requests
    @PostMapping(Routes.LOGIN)
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    // Handles signup requests and returns HTTP code
    @PostMapping(Routes.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRequestDTO registrationRequest) {
        authService.registerUser(registrationRequest);
    }

    // Handles delete requests
    @DeleteMapping(Routes.DELETE)
    public void deleteUserByUserId(@PathVariable("userId") UUID userId) {
        authService.deleteUserById(userId);
    }
}
