package com.example.authenticationservice.repository;


import com.example.authenticationservice.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRegistrationRepo extends JpaRepository<UserRegistration,Integer> {
    Optional<UserRegistration> findByEmail(String email);

    void deleteByEmail(String email);

    Optional<Object> findByFirstName(String username);
}
