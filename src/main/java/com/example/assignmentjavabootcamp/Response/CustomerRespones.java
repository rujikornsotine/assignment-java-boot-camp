package com.example.assignmentjavabootcamp.Response;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;

public class CustomerRespones {
    String custid;
    String firstname;
    String lastname;
    String username;
    String email;
    String address;
    String mobileno;
    String district;
    String sub_district;
    String province;
    String zipcode;

    public CustomerRespones() {
    }

    public CustomerRespones(CustomerEntity customerEntity) {
        this.custid = customerEntity.getCustid();
        this.firstname = customerEntity.getFirstname();
        this.lastname = customerEntity.getLastname();
        this.username = customerEntity.getUsername();
        this.email = customerEntity.getEmail();
        this.address = customerEntity.getAddress();
        this.mobileno = customerEntity.getMobileno();
        this.district = customerEntity.getDistrict();
        this.sub_district = customerEntity.getSub_district();
        this.province = customerEntity.getProvince();
        this.zipcode = customerEntity.getZipcode();
    }
}
