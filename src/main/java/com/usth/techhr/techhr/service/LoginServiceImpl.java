package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.AuthResponseDTO;
import com.usth.techhr.techhr.dto.LoginDTO;
import com.usth.techhr.techhr.security.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private JwtTokenGenerator tokenGenerator;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtTokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);
        return new AuthResponseDTO(token);
    }
}
