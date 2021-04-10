package com.jornwer.basicauthapp.model;

import com.jornwer.basicauthapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Column(name = "registered")
    private Date registeredDate;

    @Column(name = "last_login")
    private Date lastLogin;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(UserDTO dto, String password) {
        setEmail(dto.getEmail());
        setPassword(password);
        setUsername(dto.getUsername());
        setRegisteredDate(new Date());
        setLastLogin(new Date());
        setRole(Role.USER);
        setStatus(Status.ACTIVE);
    }
}
