package com.usth.techhr.techhr.repository;

import com.usth.techhr.techhr.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MajorRepository extends JpaRepository<Major, Long> {
    @Query(value = "SELECT * FROM MAJOR m WHERE m.value = :value", nativeQuery = true)
    Major findMajorByValue(@Param("value") String value);
}
