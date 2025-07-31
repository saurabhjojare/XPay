package com.xpay.userservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String DELETED_TOPIC = "user-deleted";
    private static final String CREATED_TOPIC = "user-created";

    public void sendUserDeletedEvent(String userId) {
        log.info("Sending user-deleted event for userId: {}", userId);
        kafkaTemplate.send(DELETED_TOPIC, userId);
    }

    public void sendUserCreatedEvent(String userId) {
        log.info("Sending user-created event for userId: {}", userId);
        kafkaTemplate.send(CREATED_TOPIC, userId);
    }
}

