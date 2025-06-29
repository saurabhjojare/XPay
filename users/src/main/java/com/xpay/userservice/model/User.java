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
    private ContactInfo contactInfo;
    private CustomerInfo customerInfo;
    private MerchantInfo merchantInfo;
    private AdminInfo adminInfo;
    private BankDetails bankDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContactInfo {
        private String name;
        private String email;
        private String phoneNumber;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CustomerInfo {
        private String address;
        private String dateOfBirth; // "yyyy-MM-dd"
        private String upiId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MerchantInfo {
        private String businessName;
        private String gstNumber;
        private String businessAddress;
        private String website;
        private String contactPerson;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AdminInfo {
        private String employeeId;
        private String department;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BankDetails {
        private String accountNumber;
        private String ifscCode;
        private String branchCode;
        private String bankName;
    }
}
