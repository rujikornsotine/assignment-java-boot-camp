package com.example.assignmentjavabootcamp.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorRespones {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String errorMessage;
    private String errorCode;

    public ErrorRespones() {
    }

}
