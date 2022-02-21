package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Exception.CustomerException;
import com.example.assignmentjavabootcamp.Response.CustomerRespones;
import com.example.assignmentjavabootcamp.Service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getcustomerprofile/{username}")
    public ResponseEntity<CustomerRespones>  GetCustomerProfile(@PathVariable String username) throws CustomerException {
       Optional<CustomerEntity> result = customerService.GetCustomerProfileByUsername(username);
       CustomerRespones customerRespones = new CustomerRespones(result.get());
       return ResponseEntity.ok(customerRespones);
    }

}
