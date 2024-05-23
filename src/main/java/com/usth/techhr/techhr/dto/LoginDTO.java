package com.usth.techhr.techhr.dto;

public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO(String phoneNumber, String password) {
        this.username = phoneNumber;
        this.password = password;
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
}
