package com.xpay.wallet.kafka;

import com.xpay.wallet.dto.WalletTransactionEvent;
import com.xpay.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WalletTransactionEventListener {

    private final WalletService walletService;

    @KafkaListener(topics = "transaction-events", groupId = "wallet-service-group")
    public void consume(WalletTransactionEvent event) {
        log.info("Received transaction event: {}", event);

        if ("TRANSACTION_COMPLETED".equals(event.getEventType()) && "SUCCESS".equals(event.getStatus())) {
            walletService.processTransaction(event);
        }
    }
}
