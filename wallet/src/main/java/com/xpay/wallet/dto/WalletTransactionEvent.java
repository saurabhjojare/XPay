package com.xpay.wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletTransactionEvent {
    private String transactionId;
    private String senderUserId;
    private String receiverUserId;
    private BigDecimal amount;
    private String status; // "SUCCESS", "FAILED", etc.
    private String eventType; // "TRANSACTION_COMPLETED"
}
