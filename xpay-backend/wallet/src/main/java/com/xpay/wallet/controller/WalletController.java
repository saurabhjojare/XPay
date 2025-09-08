package com.xpay.wallet.controller;


import com.xpay.wallet.dto.DepositRequestDTO;
import com.xpay.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/balance/{userId}")
    public BigDecimal getBalance(@PathVariable UUID userId) {
        return walletService.getBalance(userId);
    }

    @PostMapping("/deposit")
    public String deposit(@Valid @RequestBody DepositRequestDTO depositRequestDTO) {
        walletService.deposit(depositRequestDTO.getUserId(), depositRequestDTO.getAmount(), depositRequestDTO.getCardNumber());
        return "Deposit successful!";
    }
}
