package com.xpay.transactions.dto;

import com.xpay.transactions.enums.TransactionStatus;
import com.xpay.transactions.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private String transactionId;
    private UUID senderWalletId;
    private UUID receiverWalletId;
    private BigDecimal transactionAmount;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private LocalDateTime createdAt;
}
