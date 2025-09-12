package com.xpay.wallet.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpay.wallet.dto.event.TransactionWallet;
import com.xpay.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionConsumer {

    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "transaction-wallet", groupId = "wallet-service-group")
    public void listenSentTransactionWallet(String message) {
        try {
            TransactionWallet walletEvent = objectMapper.readValue(message, TransactionWallet.class);
            log.info("Received transaction wallet event: {}", walletEvent);
            walletService.debit(walletEvent.getSenderUserId(), walletEvent.getTransactionAmount());
        } catch (JsonProcessingException e) {
            log.error("Malformed JSON in transaction-wallet message: {} - skipping", message, e);
        } catch (Exception e) {
            log.error("Failed to process transaction-wallet message: {}", message, e);
        }
    }
}
