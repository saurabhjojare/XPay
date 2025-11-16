package com.xpay.notifications.event;

import com.xpay.notifications.dto.event.UserCreatedEventDTO;
import com.xpay.notifications.service.UserEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserNotificationConsumer {
    private UserEmailService userEmailService;

    @KafkaListener(topics = "user-created", groupId = "notification-service-group", containerFactory = "userCreatedKafkaListenerContainerFactory")
    public void consumeUserCreated(UserCreatedEventDTO userCreatedEventDTO) {
        if (userCreatedEventDTO == null) {
            log.warn("Received null UserCreatedEventDTO");
            return;
        }

        try {
            log.info("Received user-created event in Notification: {}", userCreatedEventDTO.getEmail());
            userEmailService.sendWelcomeEmail(userCreatedEventDTO);
            log.info("Welcome email successfully sent to {}", userCreatedEventDTO.getEmail());
        } catch (Exception e) {
            log.error("Failed to process user-created event for email={}", userCreatedEventDTO.getEmail(), e);
        }
    }

}
