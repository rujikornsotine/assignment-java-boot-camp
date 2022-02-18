package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
}
