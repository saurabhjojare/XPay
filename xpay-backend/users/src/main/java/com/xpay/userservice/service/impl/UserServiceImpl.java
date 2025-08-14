package com.xpay.userservice.service.impl;

import com.mongodb.DuplicateKeyException;
import com.xpay.userservice.config.PasswordUtil;
import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.kafka.UserProducer;
import com.xpay.userservice.mapper.UserMapper;
import com.xpay.userservice.model.User;
import com.xpay.userservice.repository.UserRepository;
import com.xpay.userservice.service.UserService;
import com.xpay.userservice.utility.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.xpay.userservice.utility.PaginationUtil.PaginatedResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProducer userProducer;

    @Override
    public PaginatedResponse<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> userPage = userRepository.findAll(pageable);

            List<UserResponseDTO> userDTOs = userPage.getContent()
                    .stream()
                    .map(userMapper::userResponseMapper)
                    .toList();

            return new PaginationUtil.PaginatedResponse<>(userDTOs,
                    (int) userPage.getTotalElements(),
                    userPage.getTotalPages(),
                    page,
                    size,
                    sortBy,
                    sortDir);
        } catch (Exception e) {
            log.error("Error fetching users", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching users", e);
        }
    }

    @Override
    public Boolean createUser(UserRequestDTO userRequestDTO) {
        try {
            User user = userMapper.toUserMapper(userRequestDTO);
            user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
            userRepository.save(user);
            userProducer.sendUserCreatedEvent(user.getId());
            return true;
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Email or phone number already exists");
        } catch (Exception e) {
            log.error("Error creating user", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }

    @Override
    public Boolean deleteUserById(String id) {
        try {
            if (!userRepository.existsById(id)) {
                log.warn("User not found with ID: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            userRepository.deleteById(id);
            userProducer.sendUserDeletedEvent(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
        }
    }

    @Override
    public UserResponseDTO getUserById(String id) {
        try {
            Optional<User> getUserById = userRepository.findById(id);

            if (getUserById.isPresent()) {
                return userMapper.userResponseMapper(getUserById.get());
            } else {
                log.warn("User not found with ID: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }

    @Override
    public Boolean updateUserById(String id, UserRequestDTO userRequestDTO) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                log.warn("User not found with ID: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            User existingUser = user.get();
            userMapper.updateUserFromDto(userRequestDTO, existingUser);
            userRepository.save(existingUser);
            return true;
        } catch (Exception e) {
            log.error("Error updating user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user", e);
        }
    }
}