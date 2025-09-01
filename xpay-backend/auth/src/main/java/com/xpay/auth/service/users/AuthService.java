package com.xpay.auth.service.users;

import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.model.User;
import com.xpay.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Service to load user details from database for Spring Security
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UUID userId, String username, String plainPassword) {
        return registerUser(userId,username,plainPassword, UserRole.USER);
    }

    public User registerUser(UUID userId, String username, String plainPassword, UserRole userRole) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(plainPassword);
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setPasswordHash(hashedPassword);
        user.setUserRole(userRole != null ? userRole : UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        User user = optionalUser.get();
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getUserId().toString());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPasswordHash());
        userDetails.setUserRole(user.getUserRole());
        userDetails.setUserStatus(user.getUserStatus());
        return userDetails;
    }
}
