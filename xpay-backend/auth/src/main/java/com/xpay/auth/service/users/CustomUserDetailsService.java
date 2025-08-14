package com.xpay.auth.service.users;

import com.xpay.auth.model.User;
import com.xpay.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Service to load user details from database for Spring Security
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }

            return new CustomUserDetails(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserStatus(),
                    user.getUserType());
        } catch (Exception e) {
            throw e;
        }
    }
}
