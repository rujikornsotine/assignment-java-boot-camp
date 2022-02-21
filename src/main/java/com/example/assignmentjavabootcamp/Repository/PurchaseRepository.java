package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Integer> {

    Optional<List<PurchaseEntity>> findByUsername(String username);
    Optional<PurchaseEntity> findByUsernameAndProductid(String username,String productid);
}
