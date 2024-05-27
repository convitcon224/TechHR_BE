package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.UserDTO;

import java.util.Optional;

public interface AuthService {
    Optional<UserDTO> findByUsername(String username);
    Boolean existsByPhone(String phoneNumber);
    Boolean existsByPhoneExceptOne(String phoneNumber, String role, long id);
    Boolean existsByEmail(String email);
    Boolean existsByEmailExceptOne(String email, String role, long id);
}
