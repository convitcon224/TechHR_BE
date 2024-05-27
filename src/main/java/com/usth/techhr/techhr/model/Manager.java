package com.usth.techhr.techhr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MANAGER")
public class Manager {

    @Id
    @SequenceGenerator(name = "MANAGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANAGER_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL", unique=true)
    private String email;

    @Column(name="PHONE", unique=true)
    private String phone;

    @Column(name="PASSWORD")
    private String password;

    public Manager() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
