package com.example.assignmentjavabootcamp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class PaymentDetailEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "payment_id",nullable = false)
    private PaymentEntity payment;

    private String paymentamount;
    private String paymentmethodid;
    private String accountnumber;
    private String accountname;
    private String accountexpire;
    private String cvv;
    private String firstname;
    private String lastname;
    private String paymentflow;
    private String deliveryname;
    private LocalDateTime transactiondate;
    private LocalDateTime expiredate;
    private String barcode;
    private double amount;
}
