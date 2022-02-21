package com.example.assignmentjavabootcamp.Request;

import lombok.Data;

@Data
public class ItemProductRequest {
    private String username;
    private String productid;
    private int amount;
    private int id;
}
