package com.xpay.userservice.events;

import com.xpay.userservice.dto.event.TransactionUser;
import com.xpay.userservice.event.TransactionProducer;
import com.xpay.userservice.model.User;
import com.xpay.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionConsumer {

    private final UserRepository userRepository;
    private final TransactionProducer transactionProducer;

    @KafkaListener(topics = "transaction-user", groupId = "users-service-group")
    public void listenTransactionInitiated(TransactionUser userEvent) {
        String phoneNumber = userEvent.getPhoneNumber();
        log.info("Received phone number: {}", phoneNumber);

        Optional<User> userOpt = userRepository.findUserIdByPhoneNumber(phoneNumber);

        if (userOpt.isPresent()) {
            UUID userId = userOpt.get().getUserId();
            log.info("Found receiverUserId: {}", userId);

            // Publish the resolved userId to transactions service
            transactionProducer.publishUserId(userId);
        } else {
            log.warn("No user found for phone: {}", phoneNumber);
        }
    }
}
