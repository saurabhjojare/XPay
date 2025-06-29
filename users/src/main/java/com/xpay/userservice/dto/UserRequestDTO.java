package com.xpay.userservice.dto;

import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    private UserType userType;
    private ContactInfoDTO contactInfo;
    private CustomerInfoDTO customerInfo;
    private MerchantInfoDTO merchantInfo;
    private AdminInfoDTO adminInfo;
    private BankDetailsDTO bankDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContactInfoDTO {
        private String name;
        private String email;
        private String phoneNumber;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CustomerInfoDTO {
        private String address;
        private String dateOfBirth; // Format: yyyy-MM-dd
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
