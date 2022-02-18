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
}
