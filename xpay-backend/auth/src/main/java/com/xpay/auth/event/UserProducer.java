package com.xpay.auth.event;

import com.xpay.auth.dto.event.UserCreatedEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserProducer {
    private final KafkaTemplate<String, UserCreatedEventDTO> createdTemplate;
    private final KafkaTemplate<String, UUID> deletedTemplate;

    public void sendUserCreatedEvent(UserCreatedEventDTO event) {
        createdTemplate.send("user-created", event);
        log.info("Sent UserCreatedEvent for user: {}", event);
    }

    public void sendUserDeletedEvent(UUID userId) {
        deletedTemplate.send("user-deleted", userId);
        log.info("Sent UserDeletedEvent for userId: {}", userId);
    }
}
