package com.example.assignmentjavabootcamp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ItemPayment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String productid;
    private int amount;
    private double price;
    private double discount;
    private double actual_price;
    private double sum_price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "payment_id",nullable = false)
    PaymentEntity payment;

    public ItemPayment() {
    }

    public ItemPayment(String productid, int amount, double price, double discount, double actual_price, double sum_price, String username, String paymentrefnumber) {
        this.productid = productid;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.actual_price = actual_price;
        this.sum_price = sum_price;

    }

    public ItemPayment(PurchaseEntity purchase, String username, String paymentrefnumber) {
        this.productid = purchase.getProductid();
        this.amount = purchase.getAmount();
        this.price = purchase.getPrice();
        this.discount = purchase.getDiscount();
        this.actual_price = purchase.getActual_price();
        this.sum_price = purchase.getSum_price();

    }

    public ItemPayment(PurchaseEntity purchase, PaymentEntity payment) {
        this.productid = purchase.getProductid();
        this.amount = purchase.getAmount();
        this.price = purchase.getPrice();
        this.discount = purchase.getDiscount();
        this.actual_price = purchase.getActual_price();
        this.sum_price = purchase.getSum_price();
        this.payment = payment;

    }
}
