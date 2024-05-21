package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.dto.DepartmentDTO;
import com.usth.techhr.techhr.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;

    @GetMapping(path = "/departments")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}
