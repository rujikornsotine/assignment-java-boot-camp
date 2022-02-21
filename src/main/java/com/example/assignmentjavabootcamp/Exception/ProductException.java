package com.example.assignmentjavabootcamp.Exception;

public class ProductException  extends BaseException {

    public ProductException(String message, String errorcode) {
        super(message, errorcode);
    }

    public static ProductException ProductNotFound(){
        return new ProductException("Product not found","0001");
    }
}
