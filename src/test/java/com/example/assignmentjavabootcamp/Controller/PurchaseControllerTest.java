package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.PurchaseException;
import com.example.assignmentjavabootcamp.Repository.PurchaseRepository;
import com.example.assignmentjavabootcamp.Request.ItemProductRequest;
import com.example.assignmentjavabootcamp.Service.PurchaseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PurchaseControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Mock
    PurchaseRepository purchaseRepository;

    @Test
    @DisplayName("ทดสอบ Flow Cart 1.add สินค้า 2.เช็คสินค้า" )
    void ADDFlowCart() {

        //ADD item in Cart
        ItemProductRequest request = new ItemProductRequest();
        request.setUsername("CustMock001");
        request.setProductid("PD0003");
        request.setAmount(2);

        ResponseEntity httpEntity = testRestTemplate.postForEntity("/api/purchase/additemproduct",request, ResponseEntity.class);

        assertEquals(httpEntity.getStatusCode(), HttpStatus.OK);

        //List item in cart
        PurchaseEntity[] responesGet = testRestTemplate.getForObject("/api/purchase/listbyusername/CustMock001", PurchaseEntity[].class);
        PurchaseEntity purchase =  Arrays.stream(responesGet).filter( x -> x.getProductid().equalsIgnoreCase("PD0003")).findAny().orElse(null);
        assertEquals(purchase.getUsername(),"CustMock001");


        // Edit
        ItemProductRequest requestEdit = new ItemProductRequest();
        requestEdit.setUsername("CustMock001");
        requestEdit.setProductid("PD0003");
        requestEdit.setAmount(5);
        requestEdit.setId(purchase.getId());

        ResponseEntity responesEdit = testRestTemplate.postForEntity("/api/purchase/edititemproduct", requestEdit, HttpEntity.class);
        assertEquals(responesEdit.getStatusCode(), HttpStatus.OK);

        PurchaseEntity[] responesGetEdit  = testRestTemplate.getForObject("/api/purchase/listbyusername/CustMock001", PurchaseEntity[].class);

        PurchaseEntity purchaseEdit =  Arrays.stream(responesGetEdit).filter( x -> x.getProductid().equalsIgnoreCase("PD0003")).findAny().orElse(null);

        assertEquals(purchaseEdit.getAmount(),5);

        //Delete
        ItemProductRequest requestDelete = new ItemProductRequest();
        requestEdit.setUsername("CustMock001");
        requestEdit.setProductid("PD0003");
        requestEdit.setAmount(5);
        requestEdit.setId(purchase.getId());

        ResponseEntity responesDelete = testRestTemplate.postForEntity("/api/purchase/deleteitemproduct", requestEdit, HttpEntity.class);
        assertEquals(responesEdit.getStatusCode(), HttpStatus.OK);

        PurchaseEntity[] responesGetDelete = testRestTemplate.getForObject("/api/purchase/listbyusername/CustMock001", PurchaseEntity[].class);

        PurchaseEntity purchaseDelete =  Arrays.stream(responesGetDelete).filter( x -> x.getProductid().equalsIgnoreCase("PD0003")).findAny().orElse(null);

        assertEquals(purchaseDelete == null,true);



    }




}