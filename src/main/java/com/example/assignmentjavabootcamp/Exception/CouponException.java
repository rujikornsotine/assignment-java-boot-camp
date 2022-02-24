package com.example.assignmentjavabootcamp.Exception;

public class CouponException extends BaseException{
    public CouponException(String message, String errorcode) {
        super(message, errorcode);
    }

    public static CouponException CouponNotFoundorExpire(){
        return new CouponException("Coupon not found or expire.","CP001");
    }


}
