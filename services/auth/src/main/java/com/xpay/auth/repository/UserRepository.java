package com.xpay.auth.repository;

import com.xpay.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

// Repository interface for accessing User data from the database
public interface UserRepository extends JpaRepository<Users, Long> {

    // Checks if a user with the given username already exists
    boolean existsByUsername(String username);

    // Retrieves a user by their username
    Optional<Users> findByUsername(String username);

    // Deletes a user from the database using their UUID
    void deleteByUserId(UUID userId);

    // Checks if a user with the given UUID exists in the database
    boolean existsByUserId(UUID userId);
}
