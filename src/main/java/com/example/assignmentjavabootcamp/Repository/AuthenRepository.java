package com.example.assignmentjavabootcamp.Repository;


import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenRepository extends JpaRepository<AuthenEntity,Integer> {
    Optional<AuthenEntity> findByUsername(String username);
}
