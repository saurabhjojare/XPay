package com.xpay.wallet.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpay.wallet.dto.UserRequestDTO;
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

    @KafkaListener(topics = "user-created", groupId = "wallet-service-group")
    public void listenUserCreated(String message) {
        try {
            UserRequestDTO userRequestDTO = objectMapper.readValue(message, UserRequestDTO.class);
            log.info("Received user-created event in Wallet: {}", userRequestDTO);
            walletService.createWalletForUser(userRequestDTO.getUserId());
        } catch (DuplicateKeyException e) {
            log.warn("User already exists (duplicate key) for message: {}", message);
        } catch (JsonProcessingException e) {
            log.error("Malformed JSON in user-created message: {} - skipping record", message, e);
        } catch (Exception e) {
            log.error("Failed to process user-created message: {}", message, e);
        }
    }

    @KafkaListener(topics = "user-deleted", groupId = "wallet-service-group")
    public void listenUserDeleted(UUID userId) {
        log.info("Received user-deleted event for userId: {}", userId);
        walletService.deleteWalletByUserId(userId);
    }
}
