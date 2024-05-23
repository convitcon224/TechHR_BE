package com.usth.techhr.techhr.controller;

import com.usth.techhr.techhr.dto.AuthResponseDTO;
import com.usth.techhr.techhr.dto.EmployeeDTO;
import com.usth.techhr.techhr.dto.LoginDTO;
import com.usth.techhr.techhr.security.JwtTokenGenerator;
import com.usth.techhr.techhr.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private AuthenticationManager authenticationManager;
    private JwtTokenGenerator tokenGenerator;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AuthenticationManager authenticationManager, JwtTokenGenerator tokenGenerator) {
        this.employeeService = employeeService;
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
    }

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

    @PostMapping(path = "/employee/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
