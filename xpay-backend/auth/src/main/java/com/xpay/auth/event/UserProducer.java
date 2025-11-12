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
    private final KafkaTemplate<String, Object> createdTemplate;
    private final KafkaTemplate<String, UUID> deletedTemplate;

    public void publishUserCreatedEvent(UserCreatedEventDTO userCreatedEventDTO) {
        createdTemplate.send("user-created", userCreatedEventDTO.getUserId().toString(), userCreatedEventDTO);
        log.info("Sent UserCreatedEvent for user: id={}, email={}, firstName={}, lastName={}",
                userCreatedEventDTO.getUserId(), userCreatedEventDTO.getEmail(),
                userCreatedEventDTO.getFirstName(), userCreatedEventDTO.getLastName());
    }

    public void publishUserDeletedEvent(UUID userId) {
        deletedTemplate.send("user-deleted", userId.toString(), userId);
        log.info("Published UserDeletedEvent for userId: {}", userId);
    }
}
