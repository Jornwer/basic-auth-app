package com.jornwer.basicauthapp.repository;

import com.jornwer.basicauthapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailOrUsername(String email, String username);
    Optional<User> findByEmail(String email);
}
