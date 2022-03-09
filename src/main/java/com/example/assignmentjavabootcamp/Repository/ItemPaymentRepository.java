package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.AddressPayment;
import com.example.assignmentjavabootcamp.Entity.ItemPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPaymentRepository extends JpaRepository<ItemPayment,Integer> {
   //Optional<List<ItemPayment>> findByUsernameAndPaymentrefnumber(String username, String paymentrefnumber);
}
