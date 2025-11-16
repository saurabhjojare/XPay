package com.xpay.wallet.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpay.wallet.dto.UserRequestDTO;
import com.xpay.wallet.dto.event.UserCreatedEventDTO;
import com.xpay.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserConsumer {
    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user-created", groupId = "wallet-service-group", containerFactory = "userCreatedKafkaListenerContainerFactory")
    public void consumeUserCreated(UserCreatedEventDTO userCreatedEventDTO) {
        if (userCreatedEventDTO == null) {
            log.warn("Received null UserCreatedEventDTO");
            return;
        }
        try {
            log.info("Received user-created event in Wallet: {}", userCreatedEventDTO.getUserId());

            if (userCreatedEventDTO.getUserId() == null) {
                log.warn("UserCreatedEventDTO contains null userId. Skipping.");
                return;
            }
            walletService.createWalletForUser(userCreatedEventDTO.getUserId());
            log.info("Wallet created for userId: {}", userCreatedEventDTO.getUserId());
        } catch (DuplicateKeyException e) {
            log.warn("Wallet already exists for userId {} (duplicate key)", userCreatedEventDTO.getUserId());
        } catch (Exception e) {
            log.error("Failed to process user-created event for userId {}", userCreatedEventDTO.getUserId(), e);
        }
    }

    @KafkaListener(topics = "user-deleted", groupId = "wallet-service-group", containerFactory = "uuidKafkaListenerContainerFactory")
    public void consumeUserDeleted(UUID userId) {
        try {
            log.info("Users Service received user-deleted event: {}", userId);
            walletService.deleteWalletByUserId(userId);
            log.info("Successfully deleted user with userId: {}", userId);
        } catch (Exception e) {
            log.error("Failed to process user-deleted event for userId: {}", userId, e);
        }
    }
}
