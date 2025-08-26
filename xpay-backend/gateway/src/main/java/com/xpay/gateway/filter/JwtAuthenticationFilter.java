package com.xpay.gateway.filter;

import com.xpay.gateway.exception.GlobalExceptionHandler;
import com.xpay.gateway.security.JwtTokenValidator;
import com.xpay.gateway.security.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private final TokenValidator tokenValidator;
    private final GlobalExceptionHandler exceptionHandler;

    public JwtAuthenticationFilter(TokenValidator tokenValidator, GlobalExceptionHandler exceptionHandler) {
        super(Config.class);
        this.tokenValidator = tokenValidator;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return exceptionHandler.handleException(exchange, HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);

            if (!tokenValidator.validateToken(token)) {
                return exceptionHandler.handleException(exchange, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            }

            String email = tokenValidator.extractUsername(token);
            String role = ((JwtTokenValidator) tokenValidator).extractUserType(token);

            exchange = exchange.mutate()
                    .request(builder -> builder
                            .header("X-User-Email", email)
                            .header("X-User-Role", role)
                    )
                    .build();
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
