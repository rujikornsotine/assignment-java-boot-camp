package com.example.assignmentjavabootcamp.Request;

import com.example.assignmentjavabootcamp.Entity.AddressEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String username;
    private AddressEntity address;
    private List<PurchaseEntity> purchaseList;
}
