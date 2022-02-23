package com.example.assignmentjavabootcamp.Exception;

public class PaymentException extends  BaseException {
    public PaymentException(String message, String errorcode) {
        super(message, errorcode);
    }

    public static PaymentException CustomerNotFound(){
        return new PaymentException("Customer not found.","PM001");
    }

    public static PaymentException ParameterIsNotVaild(String pamram){
        return new PaymentException(String.format("%s is not valid.",pamram),"PM002");
    }

    public static PaymentException ProductNotFound(){
        return new PaymentException("Product in purchase not found.","PM003");
    }

    public static PaymentException AddressNotFound(){
        return new PaymentException("Address not found.","PM006");
    }

    public static PaymentException PaymentMethodNotFound(){
        return new PaymentException("PaymentMethod not found.","PM004");
    }

    public static PaymentException PaymentInvalidFlow(){
        return new PaymentException("Payment invalid flow.","PM005");
    }

}
