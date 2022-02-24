package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Response.CustomerRespones;
import com.example.assignmentjavabootcamp.Response.ErrorRespones;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลของ customer ที่มี Profile ในระบบ")
    void getCustomerProfile() {
        CustomerRespones respones = testRestTemplate.getForObject("/api/customer/getcustomerprofile/CustMock001", CustomerRespones.class);
        assertEquals(respones.getUsername(),"CustMock001");
    }

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลของ customer ที่ไม่มี Data Profile ในระบบ")
    void getCustomerNotProfile() {
        ErrorRespones respones = testRestTemplate.getForObject("/api/customer/getcustomerprofile/CustMock002", ErrorRespones.class);
        assertEquals(respones.getErrorCode(),"0005");
        assertEquals(respones.getErrorMessage(),"CustomerException : Username CustMock002 Not Found.");
    }


}