package com.xpay.transactions.events;

import com.xpay.transactions.dto.events.TransactionUser;
import com.xpay.transactions.dto.events.TransactionWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransactionProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String USER_TOPIC = "transaction-user";
    private static final String WALLET_TOPIC = "transaction-wallet";

    public void publishReceiverPhone(TransactionUser userEvent) {
        // Send full object including phoneNumber, senderUserId, and transactionAmount
        kafkaTemplate.send(USER_TOPIC, userEvent.getPhoneNumber(), userEvent);
        log.info("Published TransactionUserEvent: {}", userEvent);
    }


    public void publishWalletDetails(TransactionWallet walletEvent) {
        kafkaTemplate.send(WALLET_TOPIC, walletEvent.getSenderUserId().toString(), walletEvent);
        log.info("Published TransactionWalletEvent: {}", walletEvent);
    }

}
