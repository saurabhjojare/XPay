package com.xpay.transactions.model;

import com.xpay.transactions.enums.TransactionStatus;
import com.xpay.transactions.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String transactionId;  // UUID as String for long-term scalability

    @Column(nullable = false)
    private UUID senderWalletId;

    @Column(nullable = false)
    private UUID receiverWalletId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType transactionType;  // SEND / RECEIVE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus transactionStatus;  // PENDING / SUCCESS / FAILED

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}