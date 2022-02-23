package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

@Data
public class AddressEntity {
    private String fullAddress;
    private String address;
    private String district;
    private String subdistrict;
    private String zipcode;
    private String province;
}
