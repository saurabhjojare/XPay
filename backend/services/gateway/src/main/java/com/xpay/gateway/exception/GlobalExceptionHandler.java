package com.xpay.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = "Unauthorized".getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

    public Mono<Void> handleForbidden(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = "Forbidden".getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

    public Mono<Void> handleBadRequest(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = "Bad Request".getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

    public Mono<Void> handleServerError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        byte[] bytes = "Internal Server Error".getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}