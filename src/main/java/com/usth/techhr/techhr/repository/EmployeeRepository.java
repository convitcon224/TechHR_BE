package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.deleted = 0", nativeQuery = true)
    List<Employee> findAllAvailableEmployees();

//    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.phone = :phone AND e.deleted = 0", nativeQuery = true)
//    Optional<Employee> findEmployeeByPhone(@Param("phone") String phone);
}
