package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.AddressEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Request.PaymentRequest;
import com.example.assignmentjavabootcamp.Response.CouponRespones;
import com.example.assignmentjavabootcamp.Response.CustomerRespones;
import com.example.assignmentjavabootcamp.Response.ErrorRespones;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ทดสอบการเช็ค Coupon ที่มีในระบบ")
    void checkCouponSuccess() {
        CouponRespones respones = testRestTemplate.getForObject("/api/payment/CheckCoupon/1111122222", CouponRespones.class);
        assertEquals(respones.getCouponcode(),"1111122222");
    }

    @Test
    @DisplayName("ทดสอบการเช็ค Coupon ที่มีในระบบ")
    void checkCouponFail() {
        ErrorRespones respones = testRestTemplate.getForObject("/api/payment/CheckCoupon/1212121212", ErrorRespones.class);
        assertEquals(respones.getErrorCode(),"CP001");
        assertEquals(respones.getErrorMessage(),"Coupon not found or expire.");
    }

    @Test
    @DisplayName("ทดสอบการlist PaymentMethod ที่มีในระบบ")
    void listPaymentMethod() {
        PaymentMethodEntity[] respones = testRestTemplate.getForObject("/api/payment/listpaymentmethod", PaymentMethodEntity[].class);
        assertEquals(respones.length,3);
    }

    @Test
    @DisplayName("ทดสอบการจ่ายงเินขั้นตอนแรก")
    void prepayment(){
        PaymentRequest paymentRequest = new PaymentRequest();
        AddressEntity address = new AddressEntity();
        address.setFullAddress("TestFull");
        address.setAddress("111/222");
        address.setDistrict("TestD");
        address.setSubdistrict("TestSD");
        address.setProvince("TestP");
        address.setZipcode("10000");
        paymentRequest.setUsername("CustMock001");
        paymentRequest.setAddress(address);
        List<PurchaseEntity> purchaseList = new ArrayList<PurchaseEntity>();

        PurchaseEntity  purchase =  new PurchaseEntity();
        purchase.setId(1);
        purchase.setProductid("PD0003");
        purchase.setAmount(2);
        purchaseList.add(purchase);
        paymentRequest.setPurchaseList(purchaseList);

        ResponseEntity<PaymentEntity> payment = testRestTemplate.postForEntity("/api/payment/prepayment",paymentRequest, PaymentEntity.class);
        PaymentEntity paymentEntity = payment.getBody();

        assertEquals(payment.getStatusCode(), HttpStatus.OK);
        assertEquals(true,paymentEntity.getId() > 0);
        assertEquals(paymentEntity.getUsername() ,"CustMock001");
    }
}