package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.AddressEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Request.PaymentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    private final String paymentmethodUrl = "/api/payment/listpaymentmethod";
    private final String prepaymenUrl = "/api/payment/prepayment";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ทดสอบการlist PaymentMethod ที่มีในระบบ")
    void listPaymentMethod() {
        PaymentMethodEntity[] respones = testRestTemplate.getForObject(paymentmethodUrl, PaymentMethodEntity[].class);
        assertEquals(respones.length, 3);
    }

    @Test
    @DisplayName("ทดสอบการจ่ายงเินขั้นตอนแรก")
    void prepayment() {
        String username = "CustMock001";

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

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1);
        purchase.setProductid("PD0003");
        purchase.setAmount(2);
        purchaseList.add(purchase);
        paymentRequest.setPurchaseList(purchaseList);

        ResponseEntity<PaymentEntity> payment = testRestTemplate.postForEntity(prepaymenUrl, paymentRequest, PaymentEntity.class);
        PaymentEntity paymentEntity = payment.getBody();

        assertEquals(payment.getStatusCode(), HttpStatus.OK);
        assertEquals(true, paymentEntity.getId() > 0);
        assertEquals(paymentEntity.getUsername(), username);
    }
}