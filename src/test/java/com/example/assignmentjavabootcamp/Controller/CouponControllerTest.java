package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Response.CouponRespones;
import com.example.assignmentjavabootcamp.Response.ErrorRespones;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final String conponUrl = "/api/coupon/checkcoupon/";

    @Test
    @DisplayName("ทดสอบการเช็ค Coupon ที่มีในระบบ")
    void checkCouponSuccess() {
        String couponCode = "123456789";
        CouponRespones respones = testRestTemplate.getForObject(conponUrl + couponCode, CouponRespones.class);
        assertEquals(respones.getCouponcode(),couponCode);
    }

    @Test
    @DisplayName("ทดสอบการเช็ค Coupon ที่ไม่มีในระบบ")
    void checkCouponFail() {
    String couponCode = "11111222223333";
    ErrorRespones respones = testRestTemplate.getForObject(conponUrl + couponCode, ErrorRespones.class);
    assertEquals(respones.getErrorCode(),"CP001");
    assertEquals(respones.getErrorMessage(),"Coupon not found or expire.");
    }
}