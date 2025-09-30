package com.xpay.userservice.event;

import com.xpay.userservice.dto.request.UserRequestDTO;
import com.xpay.userservice.dto.event.UserCreatedEventDTO;
import com.xpay.userservice.mapper.UserMapper;
import com.xpay.userservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserConsumer {
    private final UserService userService;
    private final UserMapper userMapper;

    @KafkaListener(topics = "user-created", groupId = "users-service-group")
    public void consumeUserCreated(UserCreatedEventDTO event) {
        try {
            log.info("Received user-created event: {}", event);
            UserRequestDTO userRequestDTO = userMapper.userRequestMapperFromEvent(event);
            userService.createUser(userRequestDTO);
            log.info("Created user for request: {}", event);
        } catch (Exception e) {
            log.error("Failed to process user-created event: {}", event, e);
        }
    }

    @KafkaListener(topics = "user-deleted", groupId = "users-service-group")
    public void consumeUserDeleted(UUID userId) {
        try {
            log.info("Users Service received user-deleted event: {}", userId);
            userService.deleteUserByUserId(userId);
            log.info("Successfully deleted user with userId: {}", userId);
        } catch (Exception e) {
            log.error("Failed to process user-deleted event for userId: {}", userId, e);
        }
    }
}
