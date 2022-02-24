package com.example.assignmentjavabootcamp;

import com.example.assignmentjavabootcamp.Exception.BaseException;
import com.example.assignmentjavabootcamp.Response.ErrorRespones;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {

    public ErrorAdviser() {
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorRespones> HandlerBaseException(BaseException ex){
        ErrorRespones errorRespones = new ErrorRespones();
        errorRespones.setErrorMessage(ex.getMessage());
        errorRespones.setErrorCode(ex.errorcode);
        errorRespones.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(errorRespones,HttpStatus.EXPECTATION_FAILED);
    }


}
