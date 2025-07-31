package com.xpay.wallet.kafka;

import com.xpay.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserConsumer {
    private final WalletService walletService;

    @KafkaListener(topics = "user-created", groupId = "wallet-service-group")
    public void listenUserCreated(String userId) {
        log.info("Received user-created event for userId: {}", userId);
         walletService.createWalletForUser(userId);
    }

    @KafkaListener(topics = "user-deleted", groupId = "wallet-service-group")
    public void listenUserDeleted(String userId) {
        log.info("Received user-deleted event for userId: {}", userId);
        walletService.deleteWalletByUserId(userId);
    }

}
