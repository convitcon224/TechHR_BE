package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController {

    @Autowired
    private AuthService authService;

    @GetMapping(path = "/test")
    public String test() {
        return authService.existsByPhoneExceptOne("01923749", "USER", 1).toString();
    }

    @GetMapping(path = "/test2")
    public String test2() {
        return authService.existsByEmailExceptOne("dung@gmail.com","USER", 781).toString();
    }
}
