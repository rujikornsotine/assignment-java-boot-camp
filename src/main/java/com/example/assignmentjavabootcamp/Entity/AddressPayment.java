package com.example.assignmentjavabootcamp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AddressPayment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String fullAddress;
    private String address;
    private String district;
    private String subdistrict;
    private String zipcode;
    private String province;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "payment_id",nullable = false)
    PaymentEntity payment;

    public AddressPayment() {
    }


    public AddressPayment(AddressEntity address,String username,String paymentrefnumber) {
        this.fullAddress = address.getFullAddress();
        this.address = address.getAddress();
        this.district = address.getDistrict();
        this.subdistrict = address.getSubdistrict();
        this.zipcode = address.getZipcode();
        this.province = address.getProvince();

    }

    public AddressPayment(AddressEntity address,PaymentEntity payment) {
        this.fullAddress = address.getFullAddress();
        this.address = address.getAddress();
        this.district = address.getDistrict();
        this.subdistrict = address.getSubdistrict();
        this.zipcode = address.getZipcode();
        this.province = address.getProvince();
        this.payment = payment;
    }
}
