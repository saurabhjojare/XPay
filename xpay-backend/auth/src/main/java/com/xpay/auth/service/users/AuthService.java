package com.xpay.auth.service.users;

import com.xpay.auth.dto.UserRequestDTO;
import com.xpay.auth.dto.event.UserCreatedEventDTO;
import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.event.UserProducer;
import com.xpay.auth.mapper.UserMapper;
import com.xpay.auth.model.User;
import com.xpay.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// Service to load user details from database for Spring Security
@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UserProducer eventPublisher;

    public Optional<User> registerUser(UserRequestDTO registrationRequest, UserRole userRole, UserStatus userStatus) {
        // Check if username already exists
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            log.warn("Username already exists: {}", registrationRequest.getUsername());
            return Optional.empty();
        }

        // Create new user
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPlainPassword()));

        if (registrationRequest.getUserRole() != null) {
            user.setUserRole(registrationRequest.getUserRole());
        }
        if (registrationRequest.getUserStatus() != null) {
            user.setUserStatus(registrationRequest.getUserStatus());
        }

        userRepository.save(user);

        UserCreatedEventDTO event = UserMapper.toUserCreatedDTO(user, registrationRequest, LocalDateTime.now());
        eventPublisher.publishUserCreatedEvent(event);

        log.info("Registered new user: {}", registrationRequest.getUsername());
        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        User user = optionalUser.get();
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserId(user.getUserId().toString());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setUserRole(user.getUserRole());
        userDetails.setUserStatus(user.getUserStatus());

        log.info("User logged in: {}", username);

        return userDetails;
    }

    @Transactional
    public boolean deleteUserById(UUID userId) {
        boolean exists = userRepository.existsByUserId(userId);
        if (!exists) {
            log.warn("User not found with userId: {}", userId);
            return false;
        }

        userRepository.deleteByUserId(userId);
        eventPublisher.publishUserDeletedEvent(userId);
        log.info("Deleted user with userId: {}", userId);
        return true;
    }
}
