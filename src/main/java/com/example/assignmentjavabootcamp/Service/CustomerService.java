package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Exception.CustomerException;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Optional<CustomerEntity> GetCustomerProfileByUsername(String username) throws CustomerException {

        Optional<CustomerEntity> customerEntity = customerRepository.findByUsername(username);

        if(customerEntity.isEmpty()) {
            throw CustomerException.CustomCustomerExceptionNotFound(username);
        }

        return customerEntity;
    }

}
