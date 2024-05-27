package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping(path = "/test")
    public String test() {
        return managerRepository.existsByPhoneExceptOne("01923749", "USER", 1).toString();
    }

    @GetMapping(path = "/test2")
    public String test2() {
        return managerRepository.existsByEmailExceptOne("dung@gmail.com","USER", 781).toString();
    }
}
