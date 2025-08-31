package com.xpay.auth.repository;


import com.xpay.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by their email
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
