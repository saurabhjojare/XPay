package com.xpay.auth.repository;


import com.xpay.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository interface for accessing User data from the database
public interface UserRepository extends JpaRepository<User, Long> {

    // Checks if a user with the given username already exists
    boolean existsByUsername(String username);

    // Retrieves a user by their username
    Optional<User> findByUsername(String username);
}
