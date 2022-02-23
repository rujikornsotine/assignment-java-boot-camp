package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.AddressEntity;
import com.example.assignmentjavabootcamp.Entity.PaymentEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Request.ComfirmPaymentRequest;
import com.example.assignmentjavabootcamp.Request.PaymentRequest;
import com.example.assignmentjavabootcamp.Service.PaymentService;
import com.example.assignmentjavabootcamp.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/prepayment")
    public HttpEntity<PaymentEntity> PrePayment(@RequestBody PaymentRequest request) throws PaymentException {
        PaymentEntity payment = paymentService.Prepayment(request.getUsername(), request.getAddress(), request.getPurchaseList());
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/confirmpayment")
    public HttpEntity<PaymentEntity> ConfirmPayment(@RequestBody ComfirmPaymentRequest request) throws PaymentException {
        PaymentEntity payment = paymentService.ConfirmPayment(request.getUsername(), request.getCouponcode(), request.getRefnumber(), request.getPaymentmethodid(), request.getAccountnumber(), request.getAccountname(), request.getAccountexpire(), request.getCvv());
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/test")
    public PaymentRequest testPaymentRequest() {
        PaymentRequest res = new PaymentRequest();
        AddressEntity address = new AddressEntity();
        address.setAddress("test");
        address.setFullAddress("test");
        address.setDistrict("test");
        address.setSubdistrict("test");
        address.setZipcode("10270");
        address.setProvince("สมุทรปราการ");
        res.setAddress(address);
        List<PurchaseEntity> purchaseEntityList = purchaseService.GetAll();
        res.setPurchaseList(purchaseEntityList);
        return res;
    }


}
