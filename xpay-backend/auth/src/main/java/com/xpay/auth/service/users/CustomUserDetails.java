package com.xpay.auth.service.users;

import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String email;
    private final String password;
    private final UserStatus userStatus;
    private final UserType userType;

    public CustomUserDetails(String id, String email, String password,
                             UserStatus userStatus, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userType));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Spring Security uses username, you can map email here
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
        return true;
    }
}
