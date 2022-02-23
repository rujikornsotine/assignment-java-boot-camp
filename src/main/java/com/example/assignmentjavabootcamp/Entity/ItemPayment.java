package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String username;
    private String paymentrefnumber;

    public ItemPayment() {
    }

    public ItemPayment(String productid, int amount, double price, double discount, double actual_price, double sum_price, String username, String paymentrefnumber) {
        this.productid = productid;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.actual_price = actual_price;
        this.sum_price = sum_price;
        this.username = username;
        this.paymentrefnumber = paymentrefnumber;
    }

    public ItemPayment(PurchaseEntity purchase, String username, String paymentrefnumber) {
        this.productid = purchase.getProductid();
        this.amount = purchase.getAmount();
        this.price = purchase.getPrice();
        this.discount = purchase.getDiscount();
        this.actual_price = purchase.getActual_price();
        this.sum_price = purchase.getSum_price();
        this.username = username;
        this.paymentrefnumber = paymentrefnumber;
    }
}
