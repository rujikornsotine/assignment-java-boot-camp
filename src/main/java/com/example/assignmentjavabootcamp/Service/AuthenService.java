package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import com.example.assignmentjavabootcamp.Repository.AuthenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenService {

    @Autowired
    private AuthenRepository authenRepository;

    public void setAuthenRepository(AuthenRepository authenRepository) {
        this.authenRepository = authenRepository;
    }

    public Boolean CheckUserAuthen(String username) {
        boolean isAuthed;
        Optional<AuthenEntity> result = authenRepository.findByUsername(username);
        isAuthed = result.isPresent();
        return isAuthed;
    }


}
