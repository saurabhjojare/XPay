package com.xpay.wallet.service;

import java.math.BigDecimal;

public interface WalletService {
    public BigDecimal getBalance(String userId);
    public void createWalletForUser(String userId);
    public void deleteWalletByUserId(String userId);
}
