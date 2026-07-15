package com.xpay.transactions.mapper;

import com.xpay.transactions.dto.TransactionRequestDTO;
import com.xpay.transactions.dto.TransactionResponseDTO;
import com.xpay.transactions.enums.TransactionStatus;
import com.xpay.transactions.enums.TransactionType;
import com.xpay.transactions.model.Transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionMapper {

    // Convert DTO to Entity
    public static Transaction toEntity(String transactionId, UUID senderUserId, TransactionRequestDTO dto) {
        return Transaction.builder()
                .transactionId(transactionId)
                .senderWalletId(senderUserId)
                .receiverWalletId(null)
                .transactionAmount(dto.getTransactionAmount())
                .transactionType(TransactionType.SENT)
                .transactionStatus(TransactionStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // Convert Entity to Response DTO
    public static TransactionResponseDTO toResponseDto(Transaction entity) {
        return TransactionResponseDTO.builder()
                .transactionId(entity.getTransactionId())
                .senderWalletId(entity.getSenderWalletId())
                .receiverWalletId(entity.getReceiverWalletId())
                .transactionAmount(entity.getTransactionAmount())
                .transactionType(entity.getTransactionType())
                .transactionStatus(entity.getTransactionStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
