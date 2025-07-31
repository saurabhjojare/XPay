package com.xpay.wallet.service;

import com.xpay.wallet.dto.WalletTransactionEvent;

import java.math.BigDecimal;

public interface WalletService {
    public void processTransaction(WalletTransactionEvent event);
    public BigDecimal getBalance(String userId);
}
