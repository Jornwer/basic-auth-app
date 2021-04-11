package com.jornwer.basicauthapp.repository;

import com.jornwer.basicauthapp.model.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeletedUserRepository extends JpaRepository<DeletedUser, Long> {
    Optional<DeletedUser> findByEmail(String email);
    void deleteByEmail(String email);
}
