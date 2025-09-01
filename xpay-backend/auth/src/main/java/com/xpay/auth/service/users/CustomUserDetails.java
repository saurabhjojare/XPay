package com.xpay.auth.service.users;

import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private UserRole userRole;
    private UserStatus userStatus;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String id, String username, String password, UserRole userRole, UserStatus userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userStatus == UserStatus.ACTIVE;
    }
}
