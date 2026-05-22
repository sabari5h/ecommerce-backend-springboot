package com.example.Ecommerce.dto.RequestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message = "username is equired")
    @Size(min = 4, max = 20, message = "Name must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "password must required")
    @Size(min = 6, message = "Password must be at least 6 characters ")
    private String password;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    public UserRequestDTO(){

    }

    public UserRequestDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
