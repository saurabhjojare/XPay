package com.xpay.auth.model;

import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.enums.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users") // MongoDB collection for storing users
public class User {

    @Id
    private String id; // Unique identifier for the user

    private UserType userType; // Type of user (e.g., ADMIN, CUSTOMER)
    private UserStatus userStatus; // Current status of the user (e.g., ACTIVE, INACTIVE)

    private String name;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String password; // User's hashed password
    private String dateOfBirth; // User's date of birth (as string for simplicity)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters
    public String getId() { return id; }
    public UserType getUserType() { return userType; }
    public UserStatus getUserStatus() { return userStatus; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCountryCode() { return countryCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public String getDateOfBirth() { return dateOfBirth; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
