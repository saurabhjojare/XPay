package com.xpay.auth.service.users;

import com.xpay.auth.dto.UserRequestDTO;
import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.dto.UserCreatedEventDTO;
import com.xpay.auth.kafka.UserProducer;
import com.xpay.auth.model.User;
import com.xpay.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// Service to load user details from database for Spring Security
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserProducer eventPublisher;

    public AuthService(UserRepository userRepository, UserProducer eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UserRequestDTO userRegistrationRequest) {
        return registerUser(userRegistrationRequest, UserRole.USER, UserStatus.ACTIVE);
    }

    public User registerUser(UserRequestDTO registrationRequest, UserRole userRole, UserStatus userStatus) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        UUID userId = UUID.randomUUID();
        String hashedPassword = passwordEncoder.encode(registrationRequest.getPlainPassword());
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUserId(userId);
        user.setUsername(registrationRequest.getUsername());
        user.setPasswordHash(hashedPassword);
        user.setUserRole(userRole != null ? userRole : UserRole.USER);
        user.setUserStatus(userStatus != null ? userStatus : UserStatus.ACTIVE);
        userRepository.save(user);

        UserCreatedEventDTO event = new UserCreatedEventDTO(
                userId, registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getEmail(),
                registrationRequest.getCountryCode(), registrationRequest.getPhoneNumber(),
                registrationRequest.getDateOfBirth(), now, now);

        eventPublisher.sendUserCreatedEvent(event);
        return user;
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
