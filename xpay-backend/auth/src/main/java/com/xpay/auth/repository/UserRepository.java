package com.xpay.auth.repository;

import com.xpay.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // Find a user by their email
    Optional<User> findByEmail(String email);
}
