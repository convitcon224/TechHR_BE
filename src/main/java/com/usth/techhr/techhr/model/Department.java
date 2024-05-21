package com.usth.techhr.techhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    public Department() {
    }

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
}
