package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String username;
    private String paymentrefnumber;

    public AddressPayment() {
    }


    public AddressPayment(AddressEntity address,String username,String paymentrefnumber) {
        this.fullAddress = address.getFullAddress();
        this.address = address.getAddress();
        this.district = address.getDistrict();
        this.subdistrict = address.getSubdistrict();
        this.zipcode = address.getZipcode();
        this.province = address.getProvince();
        this.username = username;
        this.paymentrefnumber = paymentrefnumber;
    }
}
