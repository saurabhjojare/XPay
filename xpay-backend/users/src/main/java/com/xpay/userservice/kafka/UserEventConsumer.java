package com.xpay.userservice.kafka;

import com.mongodb.DuplicateKeyException;
import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.mapper.UserMapper;
import com.xpay.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserEventConsumer {
    private final UserService userService;
    private final UserMapper userMapper;

    @KafkaListener(topics = "user-created", groupId = "user-service")
    public void consumeUserCreatedEvent(UserRequestDTO userRequestDTO) {
        try {
            userService.createUser(userRequestDTO);
        } catch (DuplicateKeyException e) {
            log.warn("User already exists: {}", userRequestDTO.getUserId());
        } catch (Exception e) {
            log.error("Error creating user from event: {}", userRequestDTO, e);
        }
    }
}
