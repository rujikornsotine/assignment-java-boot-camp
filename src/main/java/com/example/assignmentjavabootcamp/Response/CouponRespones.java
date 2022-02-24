package com.example.assignmentjavabootcamp.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponRespones {
    private String couponcode;
    private double discount;
    private String couponname;
    private String description;

    public CouponRespones(String couponcode, double discount, String couponname, String description) {
        this.couponcode = couponcode;
        this.discount = discount;
        this.couponname = couponname;
        this.description = description;
    }
}
