package com.xpay.userservice.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DuplicateKeyException;
import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.mapper.UserMapper;
import com.xpay.userservice.service.UserService;
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
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user-created", groupId = "users-service-group")
    public void listenUserCreated(String message) {
        try {
            UserRequestDTO userRequestDTO = objectMapper.readValue(message, UserRequestDTO.class);
            log.info("Created user for request: {}", userRequestDTO);
            userService.createUser(userRequestDTO);
        } catch (DuplicateKeyException e) {
            log.warn("User already exists (duplicate key) for message: {}", message);
        } catch (JsonProcessingException e) {
            log.error("Malformed JSON in user-created message: {} - skipping record", message, e);
        } catch (Exception e) {
            log.error("Failed to process user-created message: {}", message, e);
        }
    }

    @KafkaListener(topics = "user-deleted", groupId = "users-service-group")
    public void listenUserDeleted(UUID userId) {
        try {
            log.info("Users Service received user-deleted event: {}", userId);
            userService.deleteUserByUserId(userId);
            log.info("Successfully deleted user with userId: {}", userId);
        } catch (Exception e) {
            log.error("Failed to process user-deleted event for userId: {}", userId, e);
        }
    }
}
