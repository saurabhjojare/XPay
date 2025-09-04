package com.xpay.wallet.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    public BigDecimal getBalance(UUID userId);
    public void createWalletForUser(UUID userId);
    public void deleteWalletByUserId(UUID userId);
}
