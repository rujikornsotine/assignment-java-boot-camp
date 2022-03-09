package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.*;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.CustomerException;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private CouponService couponService;

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private LocalDateTime dateTime;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Transactional
    public PaymentEntity Prepayment(String username, AddressEntity address, List<PurchaseEntity> purchaseList) throws ServiceErrorException {

        PaymentEntity paymentEntity = new PaymentEntity();

        if (username == null || username.isEmpty()) {
            throw ServiceErrorException.ParameterIsNotVaild("Username");
        }

        if (purchaseList == null || purchaseList.isEmpty()) {
            throw ServiceErrorException.ProductNotFound();
        }

        if (address == null) {
            throw ServiceErrorException.AddressNotFound();
        }

        CustomerEntity cust = customerService.checkCustomerAuthen(username);

        if(purchaseList.size() > 0 ){
           long count = purchaseList.stream().filter( x -> x.getProductid() == null).count();
           if(count > 0)
               PaymentException.ProductNotFound();
        }

        double amount = purchaseList.stream().mapToDouble(x -> x.getActual_price()).sum();
        String refnumber = GenarateRefPayment();
        paymentEntity.setUsername(username);
        paymentEntity.setRefnumber(refnumber);

        PaymentDetailEntity paymentDetailEntity = new PaymentDetailEntity();
        paymentDetailEntity.setAmount(amount);
        paymentDetailEntity.setTransactiondate(LocalDateTime.now());
        paymentDetailEntity.setFirstname(cust.getFirstname());
        paymentDetailEntity.setLastname(cust.getLastname());
        paymentDetailEntity.setPaymentflow(PaymentFlow.PRE_PAYMENT.toString());
        paymentDetailEntity.setPayment(paymentEntity);

        paymentEntity.setPaymentDetail(paymentDetailEntity);

        paymentEntity.setAddressEntity(new AddressPayment(address,paymentEntity));

        List<ItemPayment> itemPayments = new ArrayList<ItemPayment>();
        for (var item : purchaseList) {
           itemPayments.add(new ItemPayment(item,paymentEntity));
        }
        paymentEntity.setItemPayments(itemPayments);

        paymentRepository.save(paymentEntity);

        return paymentEntity;

    }

    @Transactional
    public PaymentEntity ConfirmPayment(String username, String couponcode, String refnumber, String paymentmethodid, String accountnumber, String accountname, String accountexpire, String cvv) throws  ServiceErrorException {

        PaymentEntity payment = new PaymentEntity();

        if (username.isEmpty()) {
            throw ServiceErrorException.ParameterIsNotVaild("Username");
        }

        if (refnumber.isEmpty()) {
            throw ServiceErrorException.ParameterIsNotVaild("Refnumber");
        }

        if (paymentmethodid.isEmpty()) {
            throw ServiceErrorException.ParameterIsNotVaild("PaymentMethodID");
        }

        payment = getByUsernameAndRefNumber(username,refnumber);



        if(!payment.getPaymentDetail().getPaymentflow().equalsIgnoreCase(PaymentFlow.PRE_PAYMENT.toString())){
            throw ServiceErrorException.PaymentInvalidFlow();
        }

        //PaymentMethodService paymentMethodService = new PaymentMethodService();
        PaymentMethodEntity paymentMethod = paymentMethodService.getPaymentMethodByID(paymentmethodid);

        if(paymentMethod != null){
            payment.getPaymentDetail().setPaymentmethodid(paymentMethod.getPaymentmethodid());
            if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.CreditorDebit.toString())){
                payment.getPaymentDetail().setAccountnumber(accountnumber);
                payment.getPaymentDetail().setAccountname(accountname);
                payment.getPaymentDetail().setAccountexpire(accountexpire);
                payment.getPaymentDetail().setCvv(cvv);
                //call gateway
            }
            else if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.CounterService.toString()))
            {
                payment.getPaymentDetail().setBarcode("TestBarCode");
                //call CounterService gateway
            }
            else if(paymentMethod.getPaymentname().equalsIgnoreCase(PaymentMethodName.COD.toString()))
            {
                payment.getPaymentDetail().setDeliveryname("J&T");
                //call COD gateway
            }

            payment.getPaymentDetail().setPaymentflow(PaymentFlow.CONFIRM_PAYMENT.toString());
        }

        if(dateTime == null){
            dateTime = LocalDateTime.now();
        }

        Coupon coupon = couponService.CheckCoupon(couponcode);
        payment.getPaymentDetail().setAmount(payment.getPaymentDetail().getAmount() - coupon.getDiscount());

        paymentRepository.save(payment);

        return payment;
    }

    public PaymentEntity getByUsernameAndRefNumber(String username, String refnumber) throws ServiceErrorException
    {
      Optional<PaymentEntity> paymentEntity = paymentRepository.findByUsernameAndRefnumber(username,refnumber);

      if(paymentEntity.isPresent() == false)
      {
          throw ServiceErrorException.PaymentNotFound();
      }

      return paymentEntity.get();
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
