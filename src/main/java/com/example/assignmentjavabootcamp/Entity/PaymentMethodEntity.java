package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String paymentmethodid;
    private String paymentname;
    private String paymenttype;
    private String img_path;


    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(String payment_method_id, String payment_name, String payment_type, String img_path) {
        this.paymentmethodid = payment_method_id;
        this.paymentname = payment_name;
        this.paymenttype = payment_type;
        this.img_path = img_path;
    }
}
