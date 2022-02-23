package com.example.assignmentjavabootcamp.Entity;

import com.example.assignmentjavabootcamp.Service.PaymentService;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String refnumber;
    private String username;
    private String paymentamount;
    private String paymentmethodid;
    private String accountnumber;
    private String accountname;
    private String accountexpire;
    private String cvv;
    private String firstname;
    private String lastname;
    //private List<PurchaseEntity> purchaseEntityList;
    private LocalDateTime transactiondate;
    private LocalDateTime expiredate;
    private String barcode;
    private double amount;
    //private AddressEntity addressEntity;
    private String paymentflow;
    private String deliveryname;


    public PaymentEntity() {
        //this.addressEntity = new AddressEntity();
        //this.purchaseEntityList = new ArrayList<PurchaseEntity>();

    }
}
