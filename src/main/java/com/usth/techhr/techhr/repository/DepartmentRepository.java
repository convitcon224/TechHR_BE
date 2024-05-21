package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT * FROM DEPARTMENT d WHERE d.name = :name", nativeQuery = true)
    Department findDepartmentByName(@Param("name") String name);
}
