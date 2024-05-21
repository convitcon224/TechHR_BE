package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Employee;

import java.util.Map;
import java.util.stream.Stream;

public interface EmployeeRepositoryCustom {
    Stream<Employee> searchEmployeesByMajorAndDepartment(Map<String,String> params);
}
