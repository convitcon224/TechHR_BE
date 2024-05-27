package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.dto.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ManagerRepositoryCustomImpl implements ManagerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<UserDTO> findByUsername(String username) {
        Query query = entityManager.createNativeQuery("""
                SELECT * FROM (
                 SELECT e.phone AS username, e.password, 'USER' AS role FROM EMPLOYEE e
                 UNION ALL
                 SELECT m.phone AS username, m.password, 'ADMIN' AS role FROM MANAGER m)
                 WHERE username = ?""", UserDTO.class);

        query.setParameter(1, username);
        return Optional.ofNullable((UserDTO) query.getSingleResult());
    }

    @Override
    public Boolean existsByPhone(String phoneNumber) {
        Query query = entityManager.createNativeQuery("""
                SELECT CASE WHEN EXISTS
                 ( SELECT 1 FROM EMPLOYEE WHERE LOWER(phone) = LOWER(?)
                 UNION ALL
                 SELECT 1 FROM MANAGER WHERE LOWER(phone) = LOWER(?) )
                 THEN 'true' ELSE 'false' END
                 FROM dual""");
        query.setParameter(1, phoneNumber);
        query.setParameter(2, phoneNumber);
        return Boolean.parseBoolean(query.getSingleResult().toString());
    }

    @Override
    public Boolean existsByPhoneExceptOne(String phoneNumber, String role, long id) {
        StringBuilder queryBuilder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        queryBuilder.append("SELECT CASE WHEN EXISTS ( SELECT 1 FROM EMPLOYEE WHERE LOWER(phone) = LOWER(:phone_emp)");
        params.put("phone_emp", phoneNumber);
        if (role.equals("USER")) {
            queryBuilder.append(" AND id <> :id_emp");
            params.put("id_emp", id);
        }
        queryBuilder.append(" UNION ALL SELECT 1 FROM MANAGER WHERE LOWER(phone) = LOWER(:phone_ad)");
        params.put("phone_ad", phoneNumber);
        if (role.equals("ADMIN")) {
            queryBuilder.append(" AND id <> :id_ad )");
            params.put("id_ad", id);
        } else {
            queryBuilder.append(" )");
        }
        queryBuilder.append(" THEN 'true' ELSE 'false' END FROM dual");

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return Boolean.parseBoolean(query.getSingleResult().toString());
    }

    @Override
    public Boolean existsByEmail(String email) {
        Query query = entityManager.createNativeQuery("""
                SELECT CASE WHEN EXISTS
                 ( SELECT 1 FROM EMPLOYEE WHERE LOWER(email) = LOWER(?)
                 UNION ALL
                 SELECT 1 FROM MANAGER WHERE LOWER(email) = LOWER(?) )
                 THEN 'true' ELSE 'false' END
                 FROM dual""");
        query.setParameter(1, email);
        query.setParameter(2, email);
        return Boolean.parseBoolean(query.getSingleResult().toString());
    }

    @Override
    public Boolean existsByEmailExceptOne(String email, String role, long id) {
        StringBuilder queryBuilder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        queryBuilder.append("SELECT CASE WHEN EXISTS ( SELECT 1 FROM EMPLOYEE WHERE LOWER(email) = LOWER(:email_emp)");
        params.put("email_emp", email);
        if (role.equals("USER")) {
            queryBuilder.append(" AND id <> :id_emp");
            params.put("id_emp", id);
        }
        queryBuilder.append(" UNION ALL SELECT 1 FROM MANAGER WHERE LOWER(email) = LOWER(:email_ad)");
        params.put("email_ad", email);
        if (role.equals("ADMIN")) {
            queryBuilder.append(" AND id <> :id_ad )");
            params.put("id_ad", id);
        } else {
            queryBuilder.append(" )");
        }
        queryBuilder.append(" THEN 'true' ELSE 'false' END FROM dual");

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return Boolean.parseBoolean(query.getSingleResult().toString());
    }
}
