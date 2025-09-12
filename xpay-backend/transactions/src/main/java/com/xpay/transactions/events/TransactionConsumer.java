package com.xpay.transactions.events;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionConsumer {
    private final TransactionProducer transactionProducer;

    @KafkaListener(topics = "transaction-complete-user-id", groupId = "transactions-service-group")
    public void listenTransactionCompleteUserId(String userId) {
        log.info("Received completed transaction for userId: {}", userId);
    }

    @KafkaListener(topics = "transaction-initiated-user-id-by-phone-number", groupId = "transactions-service-group")
    public void listenTransactionUserResolved(UUID receiverUserId) {
        log.info("Received resolved transaction userId: {}", receiverUserId);

        // TODO: call transactionProducer.publishWalletDetails() here (we'll wire params next step)
    }
}