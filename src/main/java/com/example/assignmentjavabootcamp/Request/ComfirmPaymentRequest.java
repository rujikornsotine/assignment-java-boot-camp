package com.example.assignmentjavabootcamp.Request;

import lombok.Data;

@Data
public class ComfirmPaymentRequest {
   private String username;
   private String couponcode;
   private String refnumber;
   private String paymentmethodid;
   private String accountnumber;
   private String accountname;
   private String accountexpire;
   private String cvv;
}
