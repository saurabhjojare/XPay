package com.xpay.userservice.model;

import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    // Common fields
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private UserType userType;

    // Customer-specific fields
    private String address;
    private String dateOfBirth;

    // Merchant-specific fields
    private String businessName;
    private String gstNumber;
    private String businessAddress;
    private String website;
    private String contactPerson;

    // Admin-specific fields
    private String employeeId;
    private String department;

    // Metadata
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}