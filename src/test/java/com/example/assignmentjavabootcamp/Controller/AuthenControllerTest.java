package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Response.CheckAuthenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ทดสอบการ login ของ user ที่ login อยู่ในระบบตอนนี้")
    void checkAuthenSuccess() {
        CheckAuthenResponse response = testRestTemplate.getForObject("/api/auth/checkauthen/CustMock001", CheckAuthenResponse.class);
        assertEquals(true,response.isAuthen());
    }

    @Test
    @DisplayName("ทดสอบการ login ของ user ที่ไม่ได้ login อยู่ในระบบตอนนี้")
    void checkAuthenFail() {
        CheckAuthenResponse response = testRestTemplate.getForObject("/api/auth/checkauthen/CustMock002", CheckAuthenResponse.class);
        assertEquals(false,response.isAuthen());
    }




}