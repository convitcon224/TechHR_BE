package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.dto.MajorDTO;
import com.usth.techhr.techhr.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping(path = "/majors")
    public List<MajorDTO> getAllMajors() {
        return majorService.getAllMajors();
    }
}
