package com.example.assignmentjavabootcamp.Response;

import lombok.Data;


public class CheckAuthenResponse {
    private boolean isAuthen;
    private String username;

    public boolean isAuthen() {
        return isAuthen;
    }

    public void setAuthen(boolean isAuthen) {
        this.isAuthen = isAuthen;
    }

    public CheckAuthenResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CheckAuthenResponse(boolean isAuthen, String username) {
        this.isAuthen = isAuthen;
        this.username = username;
    }
}
