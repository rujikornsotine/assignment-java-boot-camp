package com.example.assignmentjavabootcamp.Exception;

public class AuthenExcepion extends BaseException {

    public AuthenExcepion(String message) {
        super("AuthenExcepion : " + message);
    }

    public AuthenExcepion AuthenFailed(){
        return new AuthenExcepion("authentication failed!");
    }
}
