package com.example.assignmentjavabootcamp.Exception;

public abstract class BaseException extends Exception {
    public String errorcode;
    public BaseException(String message,String errorcode){
        super(message);
        this.errorcode  = errorcode;
    }


}
