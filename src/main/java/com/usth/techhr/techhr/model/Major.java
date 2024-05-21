package com.usth.techhr.techhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MAJOR")
public class Major {
    public Major() {
    }

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="VALUE")
    private String value;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "major")
    @JsonIgnore
    private List<Employee> employees;

    public Major(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
