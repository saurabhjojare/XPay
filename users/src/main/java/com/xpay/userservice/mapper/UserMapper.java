package com.xpay.userservice.mapper;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        return User.builder()
                .userType(dto.getUserType())
                .contactInfo(
                        dto.getContactInfo() == null ? null :
                                User.ContactInfo.builder()
                                        .name(dto.getContactInfo().getName())
                                        .email(dto.getContactInfo().getEmail())
                                        .phoneNumber(dto.getContactInfo().getPhoneNumber())
                                        .password(dto.getContactInfo().getPassword())
                                        .build()
                )
                .customerInfo(
                        dto.getCustomerInfo() == null ? null :
                                User.CustomerInfo.builder()
                                        .address(dto.getCustomerInfo().getAddress())
                                        .dateOfBirth(dto.getCustomerInfo().getDateOfBirth())
                                        .upiId(dto.getCustomerInfo().getUpiId())
                                        .build()
                )
                .merchantInfo(
                        dto.getMerchantInfo() == null ? null :
                                User.MerchantInfo.builder()
                                        .businessName(dto.getMerchantInfo().getBusinessName())
                                        .gstNumber(dto.getMerchantInfo().getGstNumber())
                                        .businessAddress(dto.getMerchantInfo().getBusinessAddress())
                                        .website(dto.getMerchantInfo().getWebsite())
                                        .contactPerson(dto.getMerchantInfo().getContactPerson())
                                        .build()
                )
                .adminInfo(
                        dto.getAdminInfo() == null ? null :
                                User.AdminInfo.builder()
                                        .employeeId(dto.getAdminInfo().getEmployeeId())
                                        .department(dto.getAdminInfo().getDepartment())
                                        .build()
                )
                .bankDetails(
                        dto.getBankDetails() == null ? null :
                                User.BankDetails.builder()
                                        .accountNumber(dto.getBankDetails().getAccountNumber())
                                        .ifscCode(dto.getBankDetails().getIfscCode())
                                        .branchCode(dto.getBankDetails().getBranchCode())
                                        .bankName(dto.getBankDetails().getBankName())
                                        .build()
                )
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .userType(user.getUserType())
                .contactInfo(
                        user.getContactInfo() == null ? null :
                                UserResponseDTO.ContactInfoDTO.builder()
                                        .name(user.getContactInfo().getName())
                                        .email(user.getContactInfo().getEmail())
                                        .phoneNumber(user.getContactInfo().getPhoneNumber())
                                        .build()
                )
                .customerInfo(
                        user.getCustomerInfo() == null ? null :
                                UserResponseDTO.CustomerInfoDTO.builder()
                                        .address(user.getCustomerInfo().getAddress())
                                        .dateOfBirth(user.getCustomerInfo().getDateOfBirth())
                                        .upiId(user.getCustomerInfo().getUpiId())
                                        .build()
                )
                .merchantInfo(
                        user.getMerchantInfo() == null ? null :
                                UserResponseDTO.MerchantInfoDTO.builder()
                                        .businessName(user.getMerchantInfo().getBusinessName())
                                        .gstNumber(user.getMerchantInfo().getGstNumber())
                                        .businessAddress(user.getMerchantInfo().getBusinessAddress())
                                        .website(user.getMerchantInfo().getWebsite())
                                        .contactPerson(user.getMerchantInfo().getContactPerson())
                                        .build()
                )
                .adminInfo(
                        user.getAdminInfo() == null ? null :
                                UserResponseDTO.AdminInfoDTO.builder()
                                        .employeeId(user.getAdminInfo().getEmployeeId())
                                        .department(user.getAdminInfo().getDepartment())
                                        .build()
                )
                .bankDetails(
                        user.getBankDetails() == null ? null :
                                UserResponseDTO.BankDetailsDTO.builder()
                                        .accountNumber(user.getBankDetails().getAccountNumber())
                                        .ifscCode(user.getBankDetails().getIfscCode())
                                        .branchCode(user.getBankDetails().getBranchCode())
                                        .bankName(user.getBankDetails().getBankName())
                                        .build()
                )
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
