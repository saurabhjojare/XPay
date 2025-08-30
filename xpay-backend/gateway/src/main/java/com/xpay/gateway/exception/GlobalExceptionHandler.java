package com.xpay.gateway.exception;

import com.xpay.gateway.response.ResponseWriter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ResponseWriter responseWriter;

    public GlobalExceptionHandler(ResponseWriter writeResponse) {
        this.responseWriter = writeResponse;
    }

    public Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        return responseWriter.writeResponse(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    public Mono<Void> handleForbidden(ServerWebExchange exchange) {
        return responseWriter.writeResponse(exchange, HttpStatus.FORBIDDEN, "Forbidden");
    }

    public Mono<Void> handleBadRequest(ServerWebExchange exchange) {
        return responseWriter.writeResponse(exchange, HttpStatus.BAD_REQUEST, "Bad Request");
    }

    public Mono<Void> handleServerError(ServerWebExchange exchange) {
        return responseWriter.writeResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}