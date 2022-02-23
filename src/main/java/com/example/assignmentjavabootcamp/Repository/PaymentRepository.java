package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
    Optional<PaymentEntity> findByUsernameAndRefnumber(String username,String refnumber);
}
