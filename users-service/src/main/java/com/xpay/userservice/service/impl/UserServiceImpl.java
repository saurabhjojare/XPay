package com.xpay.userservice.service.impl;

import com.xpay.common.constants.ApiEndpoints;
import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.mapper.UserMapper;
import com.xpay.userservice.model.User;
import com.xpay.userservice.repository.UserRepository;
import com.xpay.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(userMapper::toResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching users", e);
        }
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        try {
            User user = userMapper.toEntity(userRequestDTO);
            User savedUser = userRepository.save(user);
            return userMapper.toResponseDTO(savedUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }

    @Override
    public void deleteUserById(String id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
        }
    }

    @Override
    public Optional<UserResponseDTO> getUserById(String id) {
        try {
            return userRepository.findById(id).map(userMapper::toResponseDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }
}