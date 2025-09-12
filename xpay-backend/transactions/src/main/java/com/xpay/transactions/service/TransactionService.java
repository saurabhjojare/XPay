package com.xpay.transactions.service;

import com.xpay.transactions.dto.TransactionRequestDTO;
import com.xpay.transactions.dto.TransactionResponseDTO;
import com.xpay.transactions.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO, String jwtToken);

    List<TransactionResponseDTO> getTransactionHistory(String walletId);
}
