package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
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

}
