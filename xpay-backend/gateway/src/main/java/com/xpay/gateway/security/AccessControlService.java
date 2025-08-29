package com.xpay.gateway.security;

import org.springframework.stereotype.Component;

@Component
public class AccessControlService {
    public boolean isAdminPath(String path, String role) {
        if (path.startsWith("/api/admin") && !"ROLE_ADMIN".equals(role)) {
            return false;
        }
        return true;
    }
}
