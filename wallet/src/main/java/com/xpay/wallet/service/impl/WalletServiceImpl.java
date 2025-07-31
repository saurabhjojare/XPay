package com.xpay.wallet.service.impl;

import com.xpay.wallet.dto.WalletTransactionEvent;
import com.xpay.wallet.model.Wallet;
import com.xpay.wallet.repository.WalletRepository;
import com.xpay.wallet.service.WalletService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public void processTransaction(WalletTransactionEvent event) {
        try {
            // Fetch sender wallet
            Optional<Wallet> senderOptional = walletRepository.findByUserId(event.getSenderUserId());
            if (senderOptional.isEmpty()) {
                throw new RuntimeException("Sender wallet not found");
            }
            Wallet senderWallet = senderOptional.get();

            // Fetch receiver wallet
            Optional<Wallet> receiverOptional = walletRepository.findByUserId(event.getReceiverUserId());
            if (receiverOptional.isEmpty()) {
                throw new RuntimeException("Receiver wallet not found");
            }
            Wallet receiverWallet = receiverOptional.get();

            BigDecimal amount = event.getAmount();

            // Check balance
            if (senderWallet.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            // Deduct from sender
            senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
            senderWallet.setUpdatedAt(LocalDateTime.now());

            // Credit to receiver
            receiverWallet.setBalance(receiverWallet.getBalance().add(amount));
            receiverWallet.setUpdatedAt(LocalDateTime.now());

            // Save updated wallets
            walletRepository.save(senderWallet);
            walletRepository.save(receiverWallet);

            log.info("Processed transaction: {}", event.getTransactionId());

        } catch (Exception e) {
            log.error("Error while processing transaction: " + e.getMessage());
            throw e;
        }
    }

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
}
