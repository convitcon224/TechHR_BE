package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.dto.EmployeeDTO;
import com.usth.techhr.techhr.model.Employee;
import com.usth.techhr.techhr.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable long id) {
        return employeeService.getEmployeeDTOById(id);
    }

    @GetMapping(path = "/employees")
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/employees/search")
    public List<EmployeeDTO> getEmployeeByMajorAndDepartment(@RequestParam Map<String, String> params) {
        return employeeService.getEmployeesByMajorAndDepartment(params);
    }

    @PostMapping(path = "/employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<EmployeeDTO>(employeeService.addEmployee(employeeDTO), HttpStatus.OK);
    }

    @PutMapping(path = "/employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable long id) {
        return new ResponseEntity<EmployeeDTO>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }


}
