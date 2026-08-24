package com.xpay.transactions.service.impl;

import com.xpay.transactions.dto.TransactionRequestDTO;
import com.xpay.transactions.dto.TransactionResponseDTO;
import com.xpay.transactions.mapper.TransactionMapper;
import com.xpay.transactions.model.Transaction;
import com.xpay.transactions.repository.TransactionRepository;
import com.xpay.transactions.service.TransactionService;
import com.xpay.transactions.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final JwtUtils jwtUtils;

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO, String jwtToken) {
        UUID senderUserId = UUID.fromString(jwtUtils.getUserId(jwtToken));
        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = TransactionMapper.toEntity(transactionId, senderUserId, requestDTO);
    //  transactionRepository.save(transaction);

        return TransactionMapper.toResponseDto(transaction);
    }
}
