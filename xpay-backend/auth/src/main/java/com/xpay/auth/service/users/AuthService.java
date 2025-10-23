package com.xpay.auth.service.users;

import com.xpay.auth.dto.request.UserRequestDTO;
import com.xpay.auth.dto.event.UserCreatedEventDTO;
import com.xpay.auth.event.UserProducer;
import com.xpay.auth.mapper.UserMapper;
import com.xpay.auth.model.Users;
import com.xpay.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// Service to load user details from database for Spring Security
@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserProducer eventPublisher;

    public Optional<Users> registerUser(UserRequestDTO registrationRequest) {
        // Check if username already exists
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            log.warn("Username already exists: {}", registrationRequest.getUsername());
            return Optional.empty();
        }

        // Create new user
        Users user = new Users();
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

        log.info("Registered new user: username={}, email={}, phoneNumber={}", registrationRequest.getUsername(), registrationRequest.getEmail(), registrationRequest.getPhoneNumber());
        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        Users users = optionalUser.get();
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserId(users.getUserId().toString());
        userDetails.setUsername(users.getUsername());
        userDetails.setPassword(users.getPassword());
        userDetails.setUserRole(users.getUserRole());
        userDetails.setUserStatus(users.getUserStatus());

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
