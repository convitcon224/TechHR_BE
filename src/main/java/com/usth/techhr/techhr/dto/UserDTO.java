package com.usth.techhr.techhr.dto;

public class UserDTO {
    private String username;
    private String password;
    private String role;

    public UserDTO(String phoneNumber, String password, String role) {
        this.username = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
