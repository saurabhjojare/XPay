package com.xpay.wallet.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DepositRequestDTO {
    @NotNull(message = "User ID is required")
    private UUID userId;
    @NotNull(message = "Deposit amount is required")
    @Positive(message = "Deposit amount must be greater than zero")
    @Digits(integer = 12, fraction = 0, message = "Deposit amount must be a whole number without decimals")
    private BigDecimal amount;
    @NotBlank(message = "Credit card number is required")
    @Size(min = 13, max = 19, message = "Credit card number must be between 13 and 19 digits")
    @Pattern(regexp = "\\d+", message = "Credit card number must contain only digits")
    private String cardNumber;
}
