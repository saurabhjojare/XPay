package com.xpay.userservice.service.impl;

import com.mongodb.DuplicateKeyException;
import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.xpay.userservice.utility.PaginationUtil.PaginatedResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PaginatedResponse<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> userPage = userRepository.findAll(pageable);

            List<UserResponseDTO> userDTOs = new ArrayList<>();
            for (User user : userPage.getContent()) {
                UserResponseDTO dto = userMapper.userResponseMapper(user);
                userDTOs.add(dto);
            }

            // Use PaginationUtil to handle pagination and sorting
            PaginationUtil.PaginatedResponse<UserResponseDTO> paginatedResponse =
                    PaginationUtil.paginate(userDTOs, page, size, sortBy, sortDir);

            return paginatedResponse;
        } catch (Exception e) {
            log.error("Error fetching users", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching users", e);
        }
    }

    @Override
    public Boolean createUser(UserRequestDTO userRequestDTO) {
        try {
            User user = userMapper.toUserMapper(userRequestDTO);
            userRepository.save(user);
            return true;
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Email or phone number already exists");
        } catch (Exception e) {
            log.error("Error creating user", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        try {
            Optional<User> getUserById = userRepository.findByUserId(userId);

            if (getUserById.isPresent()) {
                return userMapper.userResponseMapper(getUserById.get());
            } else {
                log.warn("User not found with ID: {}", userId);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }

    @Override
    public void deleteUserByUserId(UUID userId) {
        try {
            if (!userRepository.existsByUserId(userId)) {
                log.warn("User not found with userId: {}", userId);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            userRepository.deleteByUserId(userId);
            log.info("Deleted user with userId: {}", userId);
        } catch (Exception e) {
            log.error("Error deleting user with userId: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
        }
    }
}