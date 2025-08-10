package com.xpay.auth.controller;

import com.xpay.auth.constant.Constants;
import com.xpay.auth.dto.AuthRequest;
import com.xpay.auth.dto.AuthResponse;
import com.xpay.auth.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(Constants.AUTH)
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(Constants.LOGIN)
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        try {
            return authenticationService.authenticate(authRequest);
        } catch (AuthenticationException e) { // Spring Security's exception
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

}
