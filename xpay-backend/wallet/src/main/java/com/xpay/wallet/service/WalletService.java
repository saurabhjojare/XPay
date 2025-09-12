package com.xpay.wallet.service;

import com.xpay.wallet.dto.DepositRequestDTO;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    public BigDecimal getBalance(UUID userId);
    public void createWalletForUser(UUID userId);
    public void deleteWalletByUserId(UUID userId);
    public void deposit(UUID userId, BigDecimal amount, String cardNumber);
    void debit(UUID userId, BigDecimal amount);
    void credit(UUID receiverUserId, BigDecimal amount);
}
