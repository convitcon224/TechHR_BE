package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.EmployeeDTO;
import com.usth.techhr.techhr.exception.ObjectNotFoundException;
import com.usth.techhr.techhr.exception.UniqueConstraintViolatedException;
import com.usth.techhr.techhr.model.Employee;
import com.usth.techhr.techhr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private MajorService majorService;
    private DepartmentService departmentService;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MajorService majorService, DepartmentService departmentService, PasswordEncoder passwordEncoder, AuthService authService) {
        this.employeeRepository = employeeRepository;
        this.majorService = majorService;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty() || employee.get().getDeleted()==1) {
            throw new ObjectNotFoundException("Not found employee id "+id);
        }
        return employee.get();
    }

    @Override
    public EmployeeDTO getEmployeeDTOById(long id) {
        Employee employee = this.getEmployeeById(id);
        return this.parseToDTO(employee);
    }

//    @Override
//    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
//        employeeDTO.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
//
//        Employee employee = this.parseToEntity(employeeDTO);
//        try {
//            return this.parseToDTO(employeeRepository.save(employee));
//        } catch (Exception e) {
//            if (e.getMessage().contains("EMPLOYEE_UNIQUE_PHONE")){
//                throw new UniqueConstraintViolatedException("Unique constraint (EMPLOYEE_UNIQUE_PHONE) violated");
//            } else if (e.getMessage().contains("EMPLOYEE_UNIQUE_EMAIL")) {
//                throw new UniqueConstraintViolatedException("Unique constraint (EMPLOYEE_UNIQUE_EMAIL) violated");
//            } else {
//                throw new RuntimeException(e.getMessage());
//            }
//        }
//    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        this.validateEmailAndPhone(employeeDTO);

        employeeDTO.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        Employee employee = this.parseToEntity(employeeDTO);
        return this.parseToDTO(this.employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllAvailableEmployees().stream().map(this::parseToDTO).toList();
    }

    @Override
    public EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO) {
        this.validateUpdateEmailAndPhone(id, employeeDTO);

        Employee employeeUpdate = this.getEmployeeById(id);
        employeeUpdate.setName(employeeDTO.getName());
        employeeUpdate.setEmail(employeeDTO.getEmail());
        employeeUpdate.setPhone(employeeDTO.getPhone());
        if (employeeDTO.getMajorID() != null){
            employeeUpdate.setMajor(majorService.getMajorById(employeeDTO.getMajorID()));
        } else if (employeeDTO.getMajor() != null) {
            employeeUpdate.setMajor(majorService.getMajorByValue(employeeDTO.getMajor()));
        }
        if (employeeDTO.getDepartmentID() != null){
            employeeUpdate.setDepartment(departmentService.getDepartmentById(employeeDTO.getDepartmentID()));
        } else if (employeeDTO.getDepartment() != null) {
            employeeUpdate.setDepartment(departmentService.getDepartmentByName(employeeDTO.getDepartment()));
        }
        return this.parseToDTO(employeeRepository.saveAndFlush(employeeUpdate));
    }

    @Override
    public EmployeeDTO deleteEmployee(long id) {
        Employee employee = this.getEmployeeById(id);
        employee.setDeleted(1);
        return this.parseToDTO(employeeRepository.saveAndFlush(employee));
    }

    @Override
    public List<EmployeeDTO> getEmployeesByMajorAndDepartment(Map<String, String> params) {
        return this.employeeRepository.searchEmployeesByMajorAndDepartment(params).map(this::parseToDTO).toList();
    }

    @Override
    public void changeEmployeePassword(long id, String newPassword) {

    }

    private EmployeeDTO parseToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setMajor(employee.getMajor().getValue());
        employeeDTO.setDepartment(employee.getDepartment().getName());
        return employeeDTO;
    }

    private Employee parseToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setPassword(employeeDTO.getPassword());
        employee.setMajor(majorService.getMajorById(employeeDTO.getMajorID()));
        employee.setDepartment(departmentService.getDepartmentById(employeeDTO.getDepartmentID()));
        return employee;
    }

    private void validateEmailAndPhone(EmployeeDTO employeeDTO) {
        if (this.authService.existsByPhone(employeeDTO.getPhone())) {
            throw new UniqueConstraintViolatedException("Phone number already exists");
        }
        if (this.authService.existsByEmail(employeeDTO.getEmail())) {
            throw new UniqueConstraintViolatedException("Email already exists");
        }
    }

    private void validateUpdateEmailAndPhone(long id, EmployeeDTO employeeDTO) {
        if (this.authService.existsByPhoneExceptOne(employeeDTO.getPhone(), "USER", id)) {
            throw new UniqueConstraintViolatedException("Phone number already exists");
        }
        if (this.authService.existsByEmailExceptOne(employeeDTO.getEmail(), "USER", id)) {
            throw new UniqueConstraintViolatedException("Email already exists");
        }
    }
}
