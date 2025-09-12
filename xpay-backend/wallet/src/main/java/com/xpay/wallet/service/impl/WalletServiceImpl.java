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
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    private final TransactionProducer transactionProducer;

    public BigDecimal getBalance(UUID userId) {

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
    public void createWalletForUser(UUID userId) {
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
    public void deleteWalletByUserId(UUID userId) {
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

    @Override
    public void deposit(UUID userId, BigDecimal amount, String cardNumber) {
        try {
            Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);
            if (optionalWallet.isPresent()) {
                Wallet wallet = optionalWallet.get();
                if (wallet.getStatus() != Status.ACTIVE) {
                    throw new RuntimeException("Wallet is not active");
                }

                if (cardNumber == null || cardNumber.isBlank()) {
                    throw new RuntimeException("Invalid credit card number");
                }

                wallet.setBalance(wallet.getBalance().add(amount));
                wallet.setUpdatedAt(LocalDateTime.now());

                walletRepository.save(wallet);
                // Publish receiverUserId as string
                transactionProducer.publishUserId(userId);
                log.info("Deposited {} into wallet for user: {}", amount, userId);
            } else {
                throw new RuntimeException("Wallet not found for user " + userId);
            }
        } catch (Exception e) {
            log.error("Error while depositing: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void credit(UUID receiverUserId, BigDecimal amount) {
        try {
            Optional<Wallet> optionalWallet = walletRepository.findByUserId(receiverUserId);

            if (optionalWallet.isEmpty()) {
                throw new RuntimeException("Wallet not found for user " + receiverUserId);
            }

            Wallet wallet = optionalWallet.get();

            if (wallet.getStatus() != Status.ACTIVE) {
                throw new RuntimeException("Wallet is not active");
            }

            wallet.setBalance(wallet.getBalance().add(amount));
            wallet.setUpdatedAt(LocalDateTime.now());

            walletRepository.save(wallet);
            log.info("Credited {} into wallet for user: {}", amount, receiverUserId);

        } catch (Exception e) {
            log.error("Error while crediting wallet: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void debit(UUID userId, BigDecimal amount) {
        try {
            Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);

            if (optionalWallet.isEmpty()) {
                throw new RuntimeException("Wallet not found for user " + userId);
            }

            Wallet wallet = optionalWallet.get();

            if (wallet.getStatus() != Status.ACTIVE) {
                throw new RuntimeException("Wallet is not active");
            }

            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            wallet.setBalance(wallet.getBalance().subtract(amount));
            wallet.setUpdatedAt(LocalDateTime.now());

            walletRepository.save(wallet);

            log.info("Debited {} from wallet for user: {}", amount, userId);

        } catch (Exception e) {
            log.error("Error while debiting wallet: {}", e.getMessage());
            throw e;
        }
    }
}
