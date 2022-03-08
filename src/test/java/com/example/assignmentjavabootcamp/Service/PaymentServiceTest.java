package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.*;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class PaymentServiceTest {

    @Mock
    public PaymentRepository repository;
    @Mock
    private AuthenRepository authenRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressPaymentRepository addressPaymentRepository;
    @Mock
    private ItemPaymentRepository itemPaymentRepository;
    @Mock
    private PaymentMethodRepository paymentMethodRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private PaymentRepository paymentRepository;



    @Test
    @DisplayName("ทดสอบ service prepayment success")
    void prepayment() throws PaymentException {

        String username = "CustMock001";

        AuthenEntity authen = new AuthenEntity();
        authen.setUsername(username);

        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        AddressEntity address = new AddressEntity();
        address.setFullAddress("TestFull");
        address.setAddress("111/222");
        address.setDistrict("TestD");
        address.setSubdistrict("TestSD");
        address.setProvince("TestP");
        address.setZipcode("10000");

        List<PurchaseEntity> purchaseList = new ArrayList<PurchaseEntity>();
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1);
        purchase.setProductid("PD0003");
        purchase.setAmount(2);
        purchaseList.add(purchase);

        PaymentService service = new PaymentService();
        Mockito.when(authenRepository.findByUsername(username)).thenReturn(Optional.of(authen));
        Mockito.when(customerRepository.findByUsername(username)).thenReturn(Optional.of(customerEntity1));

        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);

        PaymentEntity payment = service.Prepayment(username, address, purchaseList);

        assertEquals(payment.getPaymentflow(),PaymentService.PaymentFlow.PRE_PAYMENT.toString());

    }

    @Test
    @DisplayName("ทดสอบ service prepayment Fail Username is not valid")
    void prepaymentFailParameterIsNotVaild() throws PaymentException {

        String username = "CustMock001";

        AuthenEntity authen = new AuthenEntity();
        authen.setUsername(username);

        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        PaymentService service = new PaymentService();
        Mockito.when(authenRepository.findByUsername(username)).thenReturn(Optional.of(authen));
        Mockito.when(customerRepository.findByUsername(username)).thenReturn(Optional.of(customerEntity1));


        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);

        //PaymentEntity payment = service.Prepayment(null, null, null);

       Exception exception = assertThrows(PaymentException.class, () -> {
           PaymentEntity payment = service.Prepayment(null, null, null);
        });

       String expectedMessage = String.format("Username is not valid.");
       String actualMessage = exception.getMessage();

       assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service prepayment Fail Address not found.")
    void prepaymentFailAddressNull() throws PaymentException {

        String username = "CustMock001";

        AuthenEntity authen = new AuthenEntity();
        authen.setUsername(username);

        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        List<PurchaseEntity> purchaseList = new ArrayList<PurchaseEntity>();
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(1);
        purchase.setProductid("PD0003");
        purchase.setAmount(2);
        purchaseList.add(purchase);

        Mockito.when(authenRepository.findByUsername(username)).thenReturn(Optional.of(authen));
        Mockito.when(customerRepository.findByUsername(username)).thenReturn(Optional.of(customerEntity1));
        PaymentService service = new PaymentService();
        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);

        Exception exception = assertThrows(PaymentException.class, () -> {
            PaymentEntity payment = service.Prepayment(username, null, purchaseList);
        });

        String expectedMessage = String.format("Address not found.");
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service prepayment Fail Product in purchase not found.")
    void prepaymentFailProductNull() throws PaymentException {

        String username = "CustMock001";

        AuthenEntity authen = new AuthenEntity();
        authen.setUsername(username);

        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        AddressEntity address = new AddressEntity();
        address.setFullAddress("TestFull");
        address.setAddress("111/222");
        address.setDistrict("TestD");
        address.setSubdistrict("TestSD");
        address.setProvince("TestP");
        address.setZipcode("10000");

        PaymentService service = new PaymentService();
        Mockito.when(authenRepository.findByUsername(username)).thenReturn(Optional.of(authen));
        Mockito.when(customerRepository.findByUsername(username)).thenReturn(Optional.of(customerEntity1));

        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);

        Exception exception = assertThrows(PaymentException.class, () -> {
            PaymentEntity payment = service.Prepayment(username, address, null);
        });

        String expectedMessage = String.format("Product in purchase not found.");
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service confirmPayment success")
    void confirmPayment() throws PaymentException {
        String username = "CustMock001";
        String refnumber = "123456789";
        String paymentMethodID = "PM001";
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity("PM001", "CreditorDebit", "01", "/image1.jpg");

        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentflow(PaymentService.PaymentFlow.PRE_PAYMENT.toString());
        payment.setRefnumber(refnumber);
        payment.setUsername(username);

        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);

        AddressPayment addressPayment = new AddressPayment();
        addressPayment.setPaymentrefnumber(refnumber);
        addressPayment.setUsername(username);
        addressPayment.setFullAddress("Test Test Test 10000");

        List<ItemPayment> itemPayments = new ArrayList<ItemPayment>();
        ItemPayment itemPayment = new ItemPayment();
        itemPayment.setPaymentrefnumber(refnumber);
        itemPayment.setUsername(username);
        itemPayment.setProductid("PD0003");
        itemPayments.add(itemPayment);

        PaymentService service = new PaymentService();
        Mockito.when(repository.findByUsernameAndRefnumber(username, refnumber)).thenReturn(
                Optional.of(payment)
        );

        Mockito.when(addressPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                Optional.of(addressPayment)
        );

        Mockito.when(itemPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                itemPayments
        );

        Mockito.when(paymentMethodRepository.findByPaymentmethodid(paymentMethodID)).thenReturn(
                Optional.of(paymentMethod)
        );

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.of(coupon)
        );

        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);
        service.setDateTime(aDateTime);
        PaymentEntity paymentEntity = service.ConfirmPayment(username, couponid, refnumber, paymentMethodID, "123456", "", "", "");

        assertEquals(payment.getPaymentflow(),PaymentService.PaymentFlow.CONFIRM_PAYMENT.toString());
    }

    @Test
    @DisplayName("ทดสอบ service confirmPayment ParameterIsNotVaild")
    void confirmPaymentFail() throws PaymentException {
        String username = "CustMock001";
        String refnumber = "123456789";
        String paymentMethodID = "PM001";
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity("PM001", "CreditorDebit", "01", "/image1.jpg");

        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentflow(PaymentService.PaymentFlow.PRE_PAYMENT.toString());
        payment.setRefnumber(refnumber);
        payment.setUsername(username);

        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);

        AddressPayment addressPayment = new AddressPayment();
        addressPayment.setPaymentrefnumber(refnumber);
        addressPayment.setUsername(username);
        addressPayment.setFullAddress("Test Test Test 10000");

        List<ItemPayment> itemPayments = new ArrayList<ItemPayment>();
        ItemPayment itemPayment = new ItemPayment();
        itemPayment.setPaymentrefnumber(refnumber);
        itemPayment.setUsername(username);
        itemPayment.setProductid("PD0003");
        itemPayments.add(itemPayment);

        PaymentService service = new PaymentService();
        Mockito.when(repository.findByUsernameAndRefnumber(username, refnumber)).thenReturn(
                Optional.of(payment)
        );

        Mockito.when(addressPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                Optional.of(addressPayment)
        );

        Mockito.when(itemPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                itemPayments
        );

        Mockito.when(paymentMethodRepository.findByPaymentmethodid(paymentMethodID)).thenReturn(
                Optional.of(paymentMethod)
        );

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.of(coupon)
        );

        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);
        service.setDateTime(aDateTime);

        Exception exception = assertThrows(PaymentException.class, () -> {
            PaymentEntity paymentEntity = service.ConfirmPayment("", couponid, refnumber, paymentMethodID, "123456", "", "", "");
        });


        String expectedMessage = "Username is not valid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service confirmPayment Payment invalid flow.")
    void confirmPaymentFailCase2() throws PaymentException {
        String username = "CustMock001";
        String refnumber = "123456789";
        String paymentMethodID = "PM001";
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity("PM001", "CreditorDebit", "01", "/image1.jpg");

        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentflow(PaymentService.PaymentFlow.CONFIRM_PAYMENT.toString());
        payment.setRefnumber(refnumber);
        payment.setUsername(username);

        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);

        AddressPayment addressPayment = new AddressPayment();
        addressPayment.setPaymentrefnumber(refnumber);
        addressPayment.setUsername(username);
        addressPayment.setFullAddress("Test Test Test 10000");

        List<ItemPayment> itemPayments = new ArrayList<ItemPayment>();
        ItemPayment itemPayment = new ItemPayment();
        itemPayment.setPaymentrefnumber(refnumber);
        itemPayment.setUsername(username);
        itemPayment.setProductid("PD0003");
        itemPayments.add(itemPayment);

        PaymentService service = new PaymentService();
        Mockito.when(repository.findByUsernameAndRefnumber(username, refnumber)).thenReturn(
                Optional.of(payment)
        );

        Mockito.when(addressPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                Optional.of(addressPayment)
        );

        Mockito.when(itemPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                itemPayments
        );

        Mockito.when(paymentMethodRepository.findByPaymentmethodid(paymentMethodID)).thenReturn(
                Optional.of(paymentMethod)
        );

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.of(coupon)
        );
        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);
        service.setDateTime(aDateTime);

        Exception exception = assertThrows(PaymentException.class, () -> {
            PaymentEntity paymentEntity = service.ConfirmPayment(username, couponid, refnumber, paymentMethodID, "123456", "", "", "");
        });


        String expectedMessage = "Payment invalid flow.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service confirmPayment PaymentMethod not found.")
    void confirmPaymentFailCase3() throws PaymentException {
        String username = "CustMock001";
        String refnumber = "123456789";
        String paymentMethodID = "PM005";
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity(paymentMethodID, "CreditorDebit", "01", "/image1.jpg");

        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentflow(PaymentService.PaymentFlow.PRE_PAYMENT.toString());
        payment.setRefnumber(refnumber);
        payment.setUsername(username);

        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);

        AddressPayment addressPayment = new AddressPayment();
        addressPayment.setPaymentrefnumber(refnumber);
        addressPayment.setUsername(username);
        addressPayment.setFullAddress("Test Test Test 10000");

        List<ItemPayment> itemPayments = new ArrayList<ItemPayment>();
        ItemPayment itemPayment = new ItemPayment();
        itemPayment.setPaymentrefnumber(refnumber);
        itemPayment.setUsername(username);
        itemPayment.setProductid("PD0003");
        itemPayments.add(itemPayment);

        PaymentService service = new PaymentService();
        Mockito.when(repository.findByUsernameAndRefnumber(username, refnumber)).thenReturn(
                Optional.of(payment)
        );

        Mockito.when(addressPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                Optional.of(addressPayment)
        );

        Mockito.when(itemPaymentRepository.findByUsernameAndPaymentrefnumber(username, refnumber)).thenReturn(
                itemPayments
        );

        Mockito.when(paymentMethodRepository.findByPaymentmethodid(paymentMethodID)).thenReturn(
                Optional.empty()
        );

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.of(coupon)
        );
        service.setAuthenRepository(authenRepository);
        service.setCustomerRepository(customerRepository);
        service.setPaymentRepository(paymentRepository);
        service.setAddressPaymentRepository(addressPaymentRepository);
        service.setItemPaymentRepository(itemPaymentRepository);
        service.setDateTime(aDateTime);

        Exception exception = assertThrows(PaymentException.class, () -> {
            PaymentEntity paymentEntity = service.ConfirmPayment(username, couponid, refnumber, paymentMethodID, "123456", "", "", "");
        });


        String expectedMessage = "PaymentMethod not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("ทดสอบ service checkCoupon success")
    void checkCoupon() throws CouponException {
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        Coupon coupon = new Coupon();
        coupon.setCouponcode("123456789");
        coupon.setCouponname("Couponส่วนลด200บาท");
        coupon.setIsexpire(false);
        coupon.setCreatedate(LocalDateTime.now());
        coupon.setExpdate(LocalDateTime.now().plusDays(20));
        coupon.setDiscount(200);

        PaymentService service = new PaymentService();

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.of(coupon)
        );

        service.setCouponRepository(couponRepository);
        service.setDateTime(aDateTime);
        Coupon coupon1 = service.CheckCoupon(couponid);

        assertTrue(coupon1 != null);

    }

    @Test
    @DisplayName("ทดสอบ service checkCoupon fail  Coupon not found or expire.")
    void checkCouponFail() throws CouponException {
        String couponid = "123456789";
        LocalDateTime aDateTime = LocalDateTime.now();

        PaymentService service = new PaymentService();

        Mockito.when(couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponid, aDateTime)).thenReturn(
                Optional.empty()
        );
        service.setDateTime(aDateTime);
        service.setCouponRepository(couponRepository);
        Exception exception = assertThrows(CouponException.class,()->{
            Coupon coupon1 = service.CheckCoupon(couponid);
        });


        String expectedMessage = "Coupon not found or expire.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


}