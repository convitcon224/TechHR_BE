package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.DepartmentDTO;
import com.usth.techhr.techhr.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(long id);

    Department getDepartmentByName(String name);

    List<DepartmentDTO> getAllDepartments();
}
