package com.xpay.auth.model;

import com.xpay.auth.enums.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private UserType userType;
    private String name;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String password;
    private String dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getId() { return id; }
    public UserType getUserType() { return userType; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCountryCode() { return countryCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public String getDateOfBirth() { return dateOfBirth; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
