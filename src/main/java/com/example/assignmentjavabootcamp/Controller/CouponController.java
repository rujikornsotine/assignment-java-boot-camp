package com.example.assignmentjavabootcamp.Controller;


import com.example.assignmentjavabootcamp.Entity.Coupon;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.CouponRepository;
import com.example.assignmentjavabootcamp.Response.CouponRespones;
import com.example.assignmentjavabootcamp.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/checkcoupon/{couponcode}")
    public ResponseEntity<CouponRespones> CheckCoupon(@PathVariable String couponcode) throws ServiceErrorException {
        Coupon result =  couponService.CheckCoupon(couponcode);
        return ResponseEntity.ok(new CouponRespones(result.getCouponcode(),result.getDiscount(),result.getCouponname(),result.getDescription()));
    }
}
