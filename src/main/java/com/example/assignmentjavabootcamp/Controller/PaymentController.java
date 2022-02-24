package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.*;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Request.ComfirmPaymentRequest;
import com.example.assignmentjavabootcamp.Request.PaymentRequest;
import com.example.assignmentjavabootcamp.Response.CouponRespones;
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
    public ResponseEntity<PaymentEntity> PrePayment(@RequestBody PaymentRequest request) throws PaymentException {
        PaymentEntity payment = paymentService.Prepayment(request.getUsername(), request.getAddress(), request.getPurchaseList());
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/confirmpayment")
    public ResponseEntity<PaymentEntity> ConfirmPayment(@RequestBody ComfirmPaymentRequest request) throws PaymentException {
        PaymentEntity payment = paymentService.ConfirmPayment(request.getUsername(), request.getCouponcode(), request.getRefnumber(), request.getPaymentmethodid(), request.getAccountnumber(), request.getAccountname(), request.getAccountexpire(), request.getCvv());
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/CheckCoupon/{couponcode}")
    public ResponseEntity<CouponRespones> CheckCoupon(@PathVariable String couponcode) throws CouponException {
        Coupon result =  paymentService.CheckCoupon(couponcode);
        return ResponseEntity.ok(new CouponRespones(result.getCouponcode(),result.getDiscount(),result.getCouponname(),result.getDescription()));
    }

    @GetMapping("/listpaymentmethod")
    public ResponseEntity<List<PaymentMethodEntity>> ListPaymentMethod()  {
        List<PaymentMethodEntity> result =  paymentService.ListPaymentMethod();
        return ResponseEntity.ok(result);
    }



    @GetMapping("/test")
    public ComfirmPaymentRequest testPaymentRequest() {
        ComfirmPaymentRequest res = new ComfirmPaymentRequest();
        return res;
    }


}
