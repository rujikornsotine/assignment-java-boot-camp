package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    Optional<ProductEntity> findByProductid(String productid);
}
