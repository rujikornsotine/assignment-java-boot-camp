package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.*;
import com.example.assignmentjavabootcamp.Exception.CouponException;
import com.example.assignmentjavabootcamp.Exception.PaymentException;
import com.example.assignmentjavabootcamp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private AuthenRepository authenRepository;
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

    public void setAuthenRepository(AuthenRepository authenRepository) {
        this.authenRepository = authenRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setPaymentMethodRepository(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void setAddressPaymentRepository(AddressPaymentRepository addressPaymentRepository) {
        this.addressPaymentRepository = addressPaymentRepository;
    }

    public void setItemPaymentRepository(ItemPaymentRepository itemPaymentRepository) {
        this.itemPaymentRepository = itemPaymentRepository;
    }

    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    private LocalDateTime dateTime;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

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

        Optional<AuthenEntity> authenEntity = authenRepository.findByUsername(username);
        if (authenEntity.isEmpty()) {
            throw PaymentException.Pleaselogin();
        }

        PaymentEntity paymentEntity = new PaymentEntity();
        CustomerEntity cust = customer.get();

        if(purchaseList.size() > 0 ){
           long count = purchaseList.stream().filter( x -> x.getProductid() == null).count();
           if(count > 0)
               PaymentException.ProductNotFound();
        }

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
            throw PaymentException.PaymentMethodNotFound();
        }

        if(paymentEntity.isPresent() ){
            payment = paymentEntity.get();
            if(!payment.getPaymentflow().equalsIgnoreCase(PaymentFlow.PRE_PAYMENT.toString())){
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

        if(dateTime == null){
            dateTime = LocalDateTime.now();
        }
        Optional<Coupon> coupon = couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponcode,dateTime);

        if(paymentMethodEntity.isPresent()){
            Coupon _coupon = coupon.get();
            payment.setAmount(payment.getAmount() - _coupon.getDiscount());
        }

        paymentRepository.save(payment);


        return payment;

    }

    public Coupon CheckCoupon(String couponcode) throws CouponException {
        if(dateTime == null){
            dateTime = LocalDateTime.now();
        }
        Optional<Coupon> coupon = couponRepository.findByCouponcodeAndExpdateAfterAndIsexpireFalse(couponcode,dateTime);
        if(coupon.isEmpty()){
            throw CouponException.CouponNotFoundorExpire();
        }

        return coupon.get();
    }

    public List<PaymentMethodEntity> ListPaymentMethod(){
        return  paymentMethodRepository.findAll();
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
