package com.example.assignmentjavabootcamp.Repository;

import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import com.example.assignmentjavabootcamp.Entity.Coupon;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    Optional<Coupon> findByCouponcodeAndExpdateAfterAndIsexpireTrue(String couponcode, LocalDateTime dateTime);
}
