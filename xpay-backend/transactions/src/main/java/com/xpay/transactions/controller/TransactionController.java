package com.xpay.transactions.controller;

import com.xpay.transactions.constants.ApiEndpoints;
import com.xpay.transactions.dto.TransactionRequestDTO;
import com.xpay.transactions.dto.TransactionResponseDTO;
import com.xpay.transactions.model.Transaction;
import com.xpay.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(ApiEndpoints.BASE_API + ApiEndpoints.Transactions.BASE_TRANSACTIONS)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(ApiEndpoints.Transactions.SEND)
    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO, @RequestHeader("Authorization") String authHeader) {
        String jwtToken = authHeader.replace("Bearer ", "");
        return transactionService.createTransaction(transactionRequestDTO, jwtToken);
    }

    @PostMapping(ApiEndpoints.Transactions.GET_TRANSACTION_HISTORY)
    public List<TransactionResponseDTO> getTransactionHistory(@PathVariable String walletId) {
        return transactionService.getTransactionHistory(walletId);
    }
}
