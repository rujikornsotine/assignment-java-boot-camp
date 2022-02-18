package com.example.assignmentjavabootcamp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class AuthenEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginTimeout;
}
