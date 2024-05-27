package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.AuthResponseDTO;
import com.usth.techhr.techhr.dto.LoginDTO;

public interface LoginService {
    AuthResponseDTO login(LoginDTO loginDTO);
}
