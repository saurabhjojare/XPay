package com.xpay.transactions.service.impl;

import com.xpay.transactions.dto.TransactionRequestDTO;
import com.xpay.transactions.dto.TransactionResponseDTO;
import com.xpay.transactions.dto.events.TransactionUser;
import com.xpay.transactions.dto.events.TransactionWallet;
import com.xpay.transactions.events.TransactionProducer;
import com.xpay.transactions.mapper.TransactionWrapper;
import com.xpay.transactions.model.Transaction;
import com.xpay.transactions.repository.TransactionRepository;
import com.xpay.transactions.service.TransactionService;
import com.xpay.transactions.util.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final JwtUtils jwtUtils;
    private final TransactionProducer transactionProducer;

    public TransactionServiceImpl(TransactionRepository transactionRepository, JwtUtils jwtUtils, TransactionProducer transactionProducer) {
        this.transactionRepository = transactionRepository;
        this.jwtUtils = jwtUtils;
        this.transactionProducer = transactionProducer;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO, String jwtToken) {
        UUID senderUserId = UUID.fromString(jwtUtils.getUserId(jwtToken));
        UUID transactionId = UUID.randomUUID();

        Transaction transaction = TransactionWrapper.toEntity(transactionId, senderUserId, requestDTO);
//        transactionRepository.save(transaction);

        // Step 1: Publish receiver phone to User Service
        transactionProducer.publishReceiverPhone(
                new TransactionUser(requestDTO.getPhoneNumber())
        );

        // Step 2: Publish transaction details to Wallet Service
        transactionProducer.publishWalletDetails(
                new TransactionWallet(senderUserId, requestDTO.getTransactionAmount());
        );

        return TransactionWrapper.toResponseDto(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getTransactionHistory(String walletId) {
        List<Transaction> transactions = transactionRepository.findBySenderWalletIdOrReceiverWalletId(walletId, walletId);
        return transactions.stream()
                .map(TransactionWrapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
