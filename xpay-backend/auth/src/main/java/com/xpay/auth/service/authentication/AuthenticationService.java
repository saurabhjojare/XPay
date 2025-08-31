package com.xpay.auth.service.authentication;

import com.xpay.auth.dto.AuthRequest;
import com.xpay.auth.dto.AuthResponse;
import com.xpay.auth.service.jwt.JwtTokenService;
import com.xpay.auth.service.users.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

// Service to handle user authentication and JWT token generation
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager; // Spring Security manager to authenticate users
    private final JwtTokenService jwtTokenService; // Service to generate JWT tokens

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    // Authenticate user and return JWT token
    public AuthResponse authenticate(AuthRequest authRequest) throws AuthenticationException {
        // Perform authentication using username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // Generate JWT token for authenticated user
        String token = jwtTokenService.generateToken(userDetails.getId(), userDetails.getUsername());
        return new AuthResponse(token); // Return token in response DTO
    }
}
