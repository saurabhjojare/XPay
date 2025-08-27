package com.xpay.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Mono<Void> writeResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

    public Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    public Mono<Void> handleBadRequest(ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.BAD_REQUEST, "Forbidden");
    }

    public Mono<Void> handleServerError(ServerWebExchange exchange) {
        return writeResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}