package com.example.assignmentjavabootcamp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class AuthenEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTimeout;

    public AuthenEntity() {
    }

    public AuthenEntity(String username) {
        this.username = username;
        this.loginDate = LocalDateTime.now();
    }
}
