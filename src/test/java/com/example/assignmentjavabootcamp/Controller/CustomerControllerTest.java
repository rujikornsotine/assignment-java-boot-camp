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

    private final String url = "/api/customer/getprofile/";

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลของ customer ที่มี Profile ในระบบ")
    void getCustomerProfile() {
        String username = "CustMock001";
        CustomerRespones respones = testRestTemplate.getForObject(url+username, CustomerRespones.class);
        assertEquals(respones.getUsername(),username);
    }

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลของ customer ที่ไม่มี Data Profile ในระบบ")
    void getCustomerNotProfile() {
        String username = "CustMock002";
        ErrorRespones respones = testRestTemplate.getForObject(url+username, ErrorRespones.class);
        assertEquals(respones.getErrorCode(),"C0001");
        assertEquals(respones.getErrorMessage(),"Username CustMock002 Not Found.");
    }


}