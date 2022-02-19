package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {

    Optional<CustomerEntity> findByUsername(String username);
}
