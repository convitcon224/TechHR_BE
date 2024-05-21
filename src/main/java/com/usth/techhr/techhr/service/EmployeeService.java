package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.EmployeeDTO;
import com.usth.techhr.techhr.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee getEmployeeById(long id);

    EmployeeDTO getEmployeeDTOById(long id);

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO);

    EmployeeDTO deleteEmployee(long id);

    List<EmployeeDTO> getEmployeesByMajorAndDepartment(Map<String, String> params);

    void changeEmployeePassword(long id, String newPassword);
}
