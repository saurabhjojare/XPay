package com.xpay.gateway.filter;

import com.xpay.gateway.exception.GlobalExceptionHandler;
import com.xpay.gateway.security.AuthorizationService;
import com.xpay.gateway.security.JwtTokenValidator;
import com.xpay.gateway.security.TokenExtractor;
import com.xpay.gateway.security.TokenValidator;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private final TokenValidator tokenValidator;
    private final TokenExtractor tokenExtractor;
    private final GlobalExceptionHandler exceptionHandler;
    private final AuthorizationService authorizationService;

    public JwtAuthenticationFilter(TokenValidator tokenValidator, TokenExtractor tokenExtractor, GlobalExceptionHandler exceptionHandler, AuthorizationService authorizationService) {
        super(Config.class);
        this.tokenValidator = tokenValidator;
        this.tokenExtractor = tokenExtractor;
        this.exceptionHandler = exceptionHandler;
        this.authorizationService = authorizationService;
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Step 1: Extract token
            String token = tokenExtractor.extractToken(
                    exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)
            );
            if (token == null) {
                return exceptionHandler.handleUnauthorized(exchange);
            }

            // Step 2: Validate token
            if (!tokenValidator.validateToken(token)) {
                return exceptionHandler.handleUnauthorized(exchange);
            }

            // Step 3: Extract claims
            String email = tokenValidator.extractUsername(token);
            String role = ((JwtTokenValidator) tokenValidator).extractUserType(token);

            // Step 4: Check authorization
            String path = exchange.getRequest().getPath().toString();
            if (!authorizationService.isAdminPath(path, role)) {
                return exceptionHandler.handleForbidden(exchange);
            }

            // Step 5: Enrich request with headers
            exchange = exchange.mutate()
                    .request(builder -> builder
                            .header("X-User-Email", email)
                            .header("X-User-Role", role)
                            .header("X-Request-ID", UUID.randomUUID().toString()))
                    .build();

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
