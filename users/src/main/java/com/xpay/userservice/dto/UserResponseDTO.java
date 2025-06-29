package com.xpay.userservice.dto;

import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private String id;
    private UserType userType;

    private ContactInfoDTO contactInfo;
    private CustomerInfoDTO customerInfo;
    private MerchantInfoDTO merchantInfo;
    private AdminInfoDTO adminInfo;
    private BankDetailsDTO bankDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContactInfoDTO {
        private String name;
        private String email;
        private String phoneNumber;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CustomerInfoDTO {
        private String address;
        private String dateOfBirth;
        private String upiId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MerchantInfoDTO {
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
    public static class AdminInfoDTO {
        private String employeeId;
        private String department;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BankDetailsDTO {
        private String accountNumber;
        private String ifscCode;
        private String branchCode;
        private String bankName;
    }
}
