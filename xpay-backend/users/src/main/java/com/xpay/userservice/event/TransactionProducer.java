package com.xpay.userservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, UUID> kafkaTemplate; // UUID as value
    private static final String TOPIC = "transaction-initiated-receiver-id";

    public void publishReceiverId(UUID receiverId) {
        kafkaTemplate.send(TOPIC, receiverId);
        log.info("Published resolved transaction userId: {}", receiverId);
    }

}