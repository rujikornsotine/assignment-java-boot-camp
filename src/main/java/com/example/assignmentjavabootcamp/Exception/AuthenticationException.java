package com.example.assignmentjavabootcamp.Exception;

public class AuthenticationException extends BaseException {

    public AuthenticationException(String message,String code) {
        super("AuthenticationException : " + message,code);
    }

    public AuthenticationException AuthenticationFail(){
        return new AuthenticationException("authentication failed!","0001");
    }
}
