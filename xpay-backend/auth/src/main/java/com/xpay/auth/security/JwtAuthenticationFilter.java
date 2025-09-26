package com.xpay.auth.security;

import com.xpay.auth.service.jwt.JwtClaimService;
import com.xpay.auth.service.jwt.JwtTokenService;
import com.xpay.auth.service.users.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

// Filter that checks JWT tokens and authenticates users for each request
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final JwtClaimService jwtClaimService;

    // Checks JWT token and sets authentication for each request
    @Override
    protected void doFilterInternal
    (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (hasBearerToken(authorizationHeader)) {
            String jwtToken = extractToken(authorizationHeader);

            if (jwtTokenService.validateToken(jwtToken)) {
                authenticateUser(jwtToken, httpServletRequest);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // Check if the Authorization header contains a Bearer token
    private boolean hasBearerToken(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    // Extract JWT token from the Authorization header
    private String extractToken(String header) {
        return header.substring(7); // Remove "Bearer "
    }

    // Authenticate the user and set it in Spring Security context
    private void authenticateUser(String token, HttpServletRequest httpServletRequest) {
        String username = jwtClaimService.extractUsername(token);
        String userId = jwtClaimService.extractUserId(token);
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserId(userId);
        userDetails.setUsername(username);
        userDetails.setPassword("");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
