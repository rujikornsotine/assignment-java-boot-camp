package com.example.assignmentjavabootcamp.Response;


import lombok.Data;

@Data
public class CheckAuthenResponse {
    private boolean Authen;
    private String username;

    public CheckAuthenResponse(boolean isAuthen, String username) {
        this.Authen = isAuthen;
        this.username = username;
    }
}
