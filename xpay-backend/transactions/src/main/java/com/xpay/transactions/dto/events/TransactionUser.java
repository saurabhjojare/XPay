package com.xpay.transactions.dto.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUser {
    private String phoneNumber;
    private UUID senderUserId;
    private BigDecimal transactionAmount;
}
