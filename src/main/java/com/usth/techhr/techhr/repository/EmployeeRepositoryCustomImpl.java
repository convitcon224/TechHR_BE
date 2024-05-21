package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.common.DataUtil;
import com.usth.techhr.techhr.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Stream<Employee> searchEmployeesByMajorAndDepartment(Map<String,String> ids) {
        StringBuilder queryBuilder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        queryBuilder.append("SELECT * from EMPLOYEE WHERE deleted=0 ");
        if(ids.containsKey("majorID") && !DataUtil.isNullOrEmpty(DataUtil.parseToLong(ids.get("majorID")))){
            queryBuilder.append(" AND majorID = :majorId");
            params.put("majorId",DataUtil.parseToLong(ids.get("majorID")));
        }
        if(ids.containsKey("departmentID") && !DataUtil.isNullOrEmpty(DataUtil.parseToLong(ids.get("departmentID")))){
            queryBuilder.append(" AND departmentID = :departmentId");
            params.put("departmentId",DataUtil.parseToLong(ids.get("departmentID")));
        }

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Employee.class);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        return query.getResultStream();
    }

}
