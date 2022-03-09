package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.Coupon;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    private LocalDateTime dateTime;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Coupon CheckCoupon(String couponcode) throws ServiceErrorException {

        if(dateTime == null){
            dateTime = LocalDateTime.now();
        }

        Optional<Coupon> coupon = couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponcode,dateTime);
        if(coupon.isEmpty()){
            throw ServiceErrorException.CouponNotFoundorExpire();
        }
        return coupon.get();
    }

}
