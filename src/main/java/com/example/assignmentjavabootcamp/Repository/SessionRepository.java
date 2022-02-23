package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity,Integer> {

}
