package com.example.assignmentjavabootcamp.Exception;

public class CustomerException extends BaseException{

    public CustomerException(String message ,String code) {
        super("CustomerException : " + message,code);
    }



    public static CustomerException CustomCustomerExceptionNotFound(String user)
    {
        return  new CustomerException(String.format("Username %s Not Found.",user),"0005");
    }
}
