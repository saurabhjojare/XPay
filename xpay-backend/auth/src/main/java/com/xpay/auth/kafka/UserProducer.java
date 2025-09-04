package com.xpay.auth.kafka;

import com.xpay.auth.dto.UserCreatedEventDTO;
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
        log.info("Publishing UserCreatedEvent for userId: {}", event.getUserId());
        createdTemplate.send("user-created", event);
    }

    public void sendUserDeletedEvent(UserCreatedEventDTO eventDTO) {
        log.info("Publishing UserDeletedEvent for userId: {}", eventDTO.getUserId());
        deletedTemplate.send("user-deleted", eventDTO.getUserId());
    }
}
