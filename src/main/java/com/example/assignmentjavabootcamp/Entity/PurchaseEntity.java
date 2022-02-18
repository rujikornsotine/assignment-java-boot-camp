package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String purchase_id;
    String username;
    String product_id;
    int amount;
    double price;
    double discount;
    double actual_price;
    double sum_price;
}
