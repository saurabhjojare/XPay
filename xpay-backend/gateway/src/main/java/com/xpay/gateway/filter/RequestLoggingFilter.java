package com.xpay.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class RequestLoggingFilter extends AbstractGatewayFilterFactory<RequestLoggingFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public RequestLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();

            String path = exchange.getRequest().getPath().toString();
            String method = exchange.getRequest().getMethod().name();

            String requestId = headers.getFirst("X-Request-ID");
            String userId = headers.getFirst("X-User-ID");
            String username = headers.getFirst("X-User-Name");
            String role = headers.getFirst("X-User-Role");
            String userStatus = headers.getFirst("X-User-Status");

            logger.info("Request received: [{} {}], User-ID: {}, Username: {}, Role: {}, " +
                            "Request-ID: {}, Status: {}",
                    method, path, userId, username, role, requestId, userStatus);

            return chain.filter(exchange).doFinally(signalType ->
                            logger.info("Request completed: [{} {}], User-ID: {}, Username: {}, " +
                                            "Role: {}, Request-ID: {}, Status: {}",
                                    method, path, userId, username, role, requestId, userStatus));
        };
    }

    public static class Config {
    }
}
