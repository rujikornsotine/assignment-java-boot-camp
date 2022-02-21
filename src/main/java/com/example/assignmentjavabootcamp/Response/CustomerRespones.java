package com.example.assignmentjavabootcamp.Response;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import lombok.Data;

@Data
public class CustomerRespones {
    private String custid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String address;
    private String mobileno;
    private String district;
    private String sub_district;
    private String province;
    private String zipcode;

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
