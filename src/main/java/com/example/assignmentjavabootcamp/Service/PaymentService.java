package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.*;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AddressPaymentRepository addressPaymentRepository;
    @Autowired
    private ItemPaymentRepository itemPaymentRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    public PaymentEntity Prepayment(String username, AddressEntity address, List<PurchaseEntity> purchaseList) throws PaymentException {

        if (username == null || username.isEmpty()) {
            throw PaymentException.ParameterIsNotVaild("Username");
        }

        if (purchaseList == null || purchaseList.isEmpty()) {
            throw PaymentException.ProductNotFound();
        }

        if (address == null) {
            throw PaymentException.AddressNotFound();
        }

        Optional<CustomerEntity> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {
            throw PaymentException.CustomerNotFound();
        }

        List<PaymentMethodEntity> paymentMethodList = paymentMethodRepository.findAll();

        if (paymentMethodList.isEmpty()) {
            throw PaymentException.PaymentMethodNotFound();
        }

        PaymentEntity paymentEntity = new PaymentEntity();
        CustomerEntity cust = customer.get();

        double amount = purchaseList.stream().mapToDouble(x -> x.getActual_price()).sum();
        String refnumber = GenarateRefPayment();
        paymentEntity.setAmount(amount);
        paymentEntity.setUsername(username);
        paymentEntity.setTransactiondate(LocalDateTime.now());
        paymentEntity.setRefnumber(refnumber);
        paymentEntity.setFirstname(cust.getFirstname());
        paymentEntity.setLastname(cust.getLastname());
        paymentEntity.setPaymentflow(PaymentFlow.PRE_PAYMENT.toString());

        paymentRepository.save(paymentEntity);
        addressPaymentRepository.save(new AddressPayment(address,username,refnumber));

        for (var item : purchaseList) {
            itemPaymentRepository.save(new ItemPayment(item,username,refnumber));
        }


        return paymentEntity;

    }

    @Transactional
    public PaymentEntity ConfirmPayment(String username, String couponcode, String refnumber, String paymentmethodid, String accountnumber, String accountname, String accountexpire, String cvv) throws PaymentException {

        PaymentEntity payment = null;
        if (username.isEmpty()) {
            throw PaymentException.ParameterIsNotVaild("Username");
        }

        if (refnumber.isEmpty()) {
            throw PaymentException.ParameterIsNotVaild("Refnumber");
        }

        if (paymentmethodid.isEmpty()) {
            throw PaymentException.ParameterIsNotVaild("PaymentMethodID");
        }

        Optional<PaymentEntity> paymentEntity = paymentRepository.findByUsernameAndRefnumber(username,refnumber);
        Optional<AddressPayment> optionalAddressPayment = addressPaymentRepository.findByUsernameAndPaymentrefnumber(username,refnumber);
        List<ItemPayment> optionalItemPayment = itemPaymentRepository.findByUsernameAndPaymentrefnumber(username,refnumber);

        if(paymentEntity.isEmpty()){
            throw PaymentException.PaymentInvalidFlow();
        }

        if(paymentEntity.isPresent() ){
            payment = paymentEntity.get();
            if(!payment.getRefnumber().equalsIgnoreCase(PaymentFlow.PRE_PAYMENT.toString())){
                throw PaymentException.PaymentInvalidFlow();
            }
        }


        Optional<PaymentMethodEntity> paymentMethodEntity = paymentMethodRepository.findByPaymentmethodid(paymentmethodid);

        if(paymentMethodEntity.isEmpty()){
            throw PaymentException.PaymentMethodNotFound();
        }

        if(paymentMethodEntity.isPresent()){
            PaymentMethodEntity paymentMethod = paymentMethodEntity.get();
            payment.setPaymentmethodid(paymentMethod.getPaymentmethodid());

            if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.CreditorDebit.toString())){
                payment.setAccountnumber(accountnumber);
                payment.setAccountname(accountname);
                payment.setAccountexpire(accountexpire);
                payment.setCvv(cvv);
                //call gateway
            }

            if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.CounterService.toString())){
                payment.setBarcode("TestBarCode");
                //call CounterService gateway
            }

            if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.COD.toString())){
                payment.setDeliveryname("J&T");
                //call COD gateway
            }


            payment.setPaymentflow(PaymentFlow.CONFIRM_PAYMENT.toString());
        }


        Optional<Coupon> coupon = couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireTrue(couponcode,LocalDateTime.now());

        if(paymentMethodEntity.isPresent()){
            Coupon _coupon = coupon.get();
            payment.setAmount(payment.getAmount() - _coupon.getDiscount());
        }

        paymentRepository.save(payment);

        return payment;

    }

    public boolean CheckCoupon(String couponcode){
        Optional<Coupon> coupon = couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireTrue(couponcode,LocalDateTime.now());
        return coupon.isPresent();
    }

    private String GenarateRefPayment() {
        return "PM" + UUID.randomUUID().toString().replace("-", "");
    }

    public enum PaymentFlow {
        PRE_PAYMENT, CONFIRM_PAYMENT, PAYMENT
    }

    public enum PaymentMethodName {
        CreditorDebit, CounterService, COD
    }


}
