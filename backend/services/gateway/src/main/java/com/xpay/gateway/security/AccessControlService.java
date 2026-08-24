package com.xpay.gateway.security;

import org.springframework.stereotype.Component;

@Component
public class AccessControlService {
    public boolean isAdminPath(String path, String role, String status) {
        if (path.startsWith("/api/admin") && (!"ACTIVE".equals(status) || !"ROLE_ADMIN".equals(role))) {
            return false;
        }
        return true;
    }

    public boolean isUserPath(String path, String role, String status) {
        if (path.startsWith("/api/users") && (!"ACTIVE".equals(status) || !"ROLE_USER".equals(role))) {
            return false;
        }
        return true;
    }
}
