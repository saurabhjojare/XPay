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
    private UserType userType;
    private String name;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String password;
    private String dateOfBirth; // "yyyy-MM-dd"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
