package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String paymentid;
    private String refid;
    private String username;
    private String paymentamount;
    private String paymentmethodid;
    private String purchaseid;

}
