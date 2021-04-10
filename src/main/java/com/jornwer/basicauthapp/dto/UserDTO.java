package com.jornwer.basicauthapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 3, max = 64, message = "Username should be 3-64 characters long")
    private String username;
    @Size(min = 1, message = "Password should be 1+ characters long")
    private String password;
}
