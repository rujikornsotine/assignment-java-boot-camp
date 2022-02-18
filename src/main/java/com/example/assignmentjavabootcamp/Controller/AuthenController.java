package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Response.CheckAuthenResponse;
import com.example.assignmentjavabootcamp.Service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthenController {
    @Autowired
    AuthenService authenService;

    @GetMapping("/checkauthen/{username}")
    public CheckAuthenResponse CheckAuthen(@PathVariable String username){
       boolean result = authenService.CheckUserAuthen(username);
       return new CheckAuthenResponse(result,username);
    }


}
