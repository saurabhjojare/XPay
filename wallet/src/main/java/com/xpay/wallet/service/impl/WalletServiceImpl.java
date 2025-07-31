package com.xpay.wallet.service.impl;

import com.xpay.wallet.constants.Status;
import com.xpay.wallet.model.Wallet;
import com.xpay.wallet.repository.WalletRepository;
import com.xpay.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    public BigDecimal getBalance(String userId) {

        try {
            Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);

            if (optionalWallet.isPresent()) {
                Wallet wallet = optionalWallet.get();
                return wallet.getBalance();
            } else {
                throw new RuntimeException("Wallet not found");
            }
        } catch (Exception e) {
            log.error("Error while fetching balance: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void createWalletForUser(String userId) {
        Optional<Wallet> existingWallet = walletRepository.findByUserId(userId);

        if (existingWallet.isEmpty()) {
            Wallet wallet = Wallet.builder()
                    .userId(userId)
                    .balance(BigDecimal.ZERO)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .Status(Status.ACTIVE)
                    .build();

            walletRepository.save(wallet);
            log.info("Created new wallet for user: {}", userId);
        } else {
            log.info("Wallet already exists for user: {}", userId);
        }
    }

    @Override
    public void deleteWalletByUserId(String userId) {
        try {
            Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);

            if (optionalWallet.isPresent()) {
                Wallet wallet = optionalWallet.get();
                walletRepository.delete(wallet);
                log.info("Wallet deleted for user: {}", userId);
            } else {
                log.warn("No wallet found to delete for user: {}", userId);
            }
        } catch (Exception e) {
            log.error("Error while deleting wallet for user {}: {}", userId, e.getMessage());
            throw e;
        }
    }
}
