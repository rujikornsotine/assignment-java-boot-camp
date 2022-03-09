package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.AddressPayment;
import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressPaymentRepository extends JpaRepository<AddressPayment,Integer> {
   // Optional<AddressPayment> findByUsernameAndPaymentrefnumber(String username,String paymentrefnumber);
}
