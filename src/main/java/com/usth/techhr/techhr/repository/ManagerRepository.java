package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long>, ManagerRepositoryCustom {

}
