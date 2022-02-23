package com.example.assignmentjavabootcamp;

import com.example.assignmentjavabootcamp.Entity.Coupon;
import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Repository.CouponRepository;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import com.example.assignmentjavabootcamp.Repository.PaymentMethodRepository;
import com.example.assignmentjavabootcamp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CouponRepository couponRepository;


    @Override
    public void run(ApplicationArguments args) {
        SetProductData();
        SetPaymentMethodData();
        SetCustomerData();
    }

    private void SetProductData() {

        productRepository.save(new ProductEntity("PD0001", "NEW BALANCE FuelCell RC Elite V2",
                "อัปเปอร์ทำจากผ้าถักระบายอากาศได้ดี โฟม FuelCell ให้ความรู้สึกคล่องตัวเพื่อช่วยให้คุณก้าวไปข้างหน้า", "NEW BALANCE", "FREE", "black",
                10, 7490.00, 500, "2022-03-31", "Male", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", true));

        productRepository.save(new ProductEntity("PD0002", "NIKE Air Zoom Pegasus 38",
                "อัปเปอร์ผ้าตาข่ายระบายอากาศได้ดี สวมใส่สบาย เทคโนโลยี NIKE React โฟมน้ำหนักเบาและนุ่มเด้งที่มีความ", "NIKE", "FREE", "black",
                5, 7490.00, 3000.00, "2022-03-31", "Male", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", true));

        productRepository.save(new ProductEntity("PD0003", "HOKA Rincon 3 Wide",
                "ผลิตจากวัสดุมังสวิรัติ 100% อัปเปอร์ทำจากผ้าตาข่ายมอบความเบาเป็นพิเศษและระบายอากาศได้ดี", "HOKA", "FREE", "black",
                5, 4490.00, 300.00, "2022-03-31", "Male", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", false));

        productRepository.save(new ProductEntity("PD0004", "NEW BALANCE FuelCell RC Elite V2",
                "อัปเปอร์ทำจากผ้าถักระบายอากาศได้ดี โฟม FuelCell ให้ความรู้สึกคล่องตัวเพื่อช่วยให้คุณก้าวไปข้างหน้า", "NEW BALANCE", "FREE", "black",
                5, 7490.00, 300.00, "2022-03-31", "FeMale", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", true));

        productRepository.save(new ProductEntity("PD0005", "NIKE ZoomX Vaporfly Next",
                "อัปเปอร์ดีไซน์ใหม่โดดเด่นด้วยผ้าตาข่ายที่จัดวางในบริเวณที่คุณต้องการการระบายอากาศเป็นพิเศษ มอบการออกแบบที่นุ่มนวลและเย็นสบายกว่าขึ้นเดิม", "NIKE", "FREE", "black",
                5, 6490.00, 300.00, "2022-03-31", "FeMale", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", true));

        productRepository.save(new ProductEntity("PD0006", "HOKA Rincon 3 Wide",
                "ผลิตจากวัสดุมังสวิรัติ 100% อัปเปอร์ทำจากผ้าตาข่ายมอบความเบาเป็นพิเศษและระบายอากาศได้ดี", "HOKA", "FREE", "black",
                5, 5490.00, 300.00, "2022-03-31", "FeMale", "Running Shoes", "/image1.jpg",
                "/image2.jpg", "/image3.jpg", "/image4.jpg", "/image5.jpg", false));
    }

    private void SetPaymentMethodData() {
        paymentMethodRepository.save(new PaymentMethodEntity("PM001","CreditorDebit","01","/image1.jpg"));
        paymentMethodRepository.save(new PaymentMethodEntity("PM002","CounterService","02","/image1.jpg"));
        paymentMethodRepository.save(new PaymentMethodEntity("PM002","COD","03","/image1.jpg"));
    }

    private void  SetCustomerData(){
        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid("CustMock001");
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername("CustMock001");
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");
        customerRepository.save(customerEntity1);
    }

    private void  SetCouponData(){
        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);
        couponRepository.save(coupon);

        Coupon coupon2 = new Coupon();
        coupon2.setCouponcode("1111122222");
        coupon2.setCouponname("Couponส่วนลด50บาท");
        coupon2.setIsexpire(false);
        coupon2.setCreatedate(LocalDateTime.now());
        coupon2.setExpdate(LocalDateTime.now().plusDays(20));
        coupon2.setDiscount(50);
        couponRepository.save(coupon2);
    }

}
