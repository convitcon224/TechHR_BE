package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.deleted = 0", nativeQuery = true)
    List<Employee> findAllAvailableEmployees();
}
