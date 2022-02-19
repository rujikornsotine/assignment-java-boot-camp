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
    private String payment_method_id;
    private String payment_name;
    private String payment_type;
    private String img_path;

    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(String payment_method_id, String payment_name, String payment_type, String img_path) {
        this.payment_method_id = payment_method_id;
        this.payment_name = payment_name;
        this.payment_type = payment_type;
        this.img_path = img_path;
    }
}
