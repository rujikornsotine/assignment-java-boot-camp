package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import com.example.assignmentjavabootcamp.Response.ProductResponse;
import com.example.assignmentjavabootcamp.Response.ProductResponseList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลสินค้าเพื่อมาแสดงที่หน้าแรก")
    void listproductByRecommend() {
        ProductResponseList respones = testRestTemplate.getForObject("/api/product/listproductbyrecommend", ProductResponseList.class);
        assertEquals(respones.getProductResponseList().size() > 0,true);
    }

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลสินค้าจากคำค้นหา")
    void listproductByKeyword() {

        ProductResponseList respones = testRestTemplate.getForObject("/api/product/listproductbykeyword/NIKE", ProductResponseList.class);

        long count = respones.getProductResponseList().stream().filter( x -> x.getProductname().contains("NIKE")).count();

        assertEquals(respones.getProductResponseList().size() ,2);
        assertEquals(count,respones.getProductResponseList().size());
    }

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลสินค้าด้วย ID")
    void getProductByID() {
        ProductResponse respones = testRestTemplate.getForObject("/api/product/getproductbyid/PD0005", ProductResponse.class);
        assertEquals(respones.getProductid() ,"PD0005");
    }

    @Test
    @DisplayName("ทดสอบการดึงข้อมูลสินค้าสำหรับแสดงในตอนจ่ายเงิน")
    void listProductBysellProductGroup() {
        ProductResponseList respones = testRestTemplate.getForObject("/api/product/listproductbysellproductgroup/NIKE/PD0005", ProductResponseList.class);
        long count = respones.getProductResponseList().stream().filter(x -> x.getBand().equalsIgnoreCase("NIKE")).distinct().count();
        assertEquals(count ,1);
    }
}