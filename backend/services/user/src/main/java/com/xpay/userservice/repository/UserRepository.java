package com.xpay.userservice.repository;

import com.xpay.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    void deleteByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
    Optional<User> findByUserId(UUID userId);
}
