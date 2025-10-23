package com.xpay.gateway.filter;

import com.xpay.gateway.exception.GlobalExceptionHandler;
import com.xpay.gateway.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenExtractor jwtTokenExtractor;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final AccessControlService accessControlService;
    private final JwtClaimExtractor jwtClaimExtractor;

    public JwtAuthenticationFilter(JwtTokenValidator jwttokenValidator, JwtTokenExtractor tokenExtractor,
                                   GlobalExceptionHandler exceptionHandler, AccessControlService authorizationService,
                                   JwtClaimExtractor extractClaims) {
        super(Config.class);
        this.jwtTokenValidator = jwttokenValidator;
        this.jwtTokenExtractor = tokenExtractor;
        this.globalExceptionHandler = exceptionHandler;
        this.accessControlService = authorizationService;
        this.jwtClaimExtractor = extractClaims;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Step 1: Extract token
            String token = jwtTokenExtractor.extractToken(
                    exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

            if (token == null) {
                return globalExceptionHandler.handleUnauthorized(exchange);
            }

            // Step 2: Validate token
            if (!jwtTokenValidator.validateToken(token)) {
                return globalExceptionHandler.handleUnauthorized(exchange);
            }

            // Step 3: Extract claims
            String userName = jwtClaimExtractor.extractUsername(token);
            String userRole = jwtClaimExtractor.extractUserRole(token);
            String userId = jwtClaimExtractor.extractUserId(token);
            String userStatus = jwtClaimExtractor.extractUserStatus(token);

            // Step 4: Check authorization
            String path = exchange.getRequest().getPath().toString();
            if (!accessControlService.isAdminPath(path, userRole, userStatus) &&
                    !accessControlService.isUserPath(path, userRole, userStatus)) {
                return globalExceptionHandler.handleForbidden(exchange);
            }

            // Step 5: Enrich request with headers
            exchange = exchange.mutate().request(builder -> builder
                            .header("X-User-Name", userName)
                            .header("X-User-Role", userRole)
                            .header("X-User-ID", userId)
                            .header("X-User-Status", userStatus)
                            .header("X-Request-ID", UUID.randomUUID().toString())
                    ).build();

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
