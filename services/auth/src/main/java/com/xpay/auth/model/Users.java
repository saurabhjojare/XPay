package com.xpay.auth.model;

import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole = UserRole.USER; // default value

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE; // default value
}
