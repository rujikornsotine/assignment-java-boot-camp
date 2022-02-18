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
    int id;
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

}
