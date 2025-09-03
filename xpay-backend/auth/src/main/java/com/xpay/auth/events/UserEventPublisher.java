package com.xpay.auth.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserEventPublisher {
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void publishUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("user-created", event);
        log.info("Published UserCreatedEvent for userId: {}", event.getUserId());
    }
}
