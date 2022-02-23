package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity,Integer> {
    Optional<PaymentMethodEntity> findByPaymentmethodid(String paymentmethodid);

}
