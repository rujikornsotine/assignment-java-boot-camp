package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Exception.CustomerException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AuthenService authenService;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerEntity GetCustomerProfileByUsername(String username) throws ServiceErrorException {

        Optional<CustomerEntity> customerEntity = customerRepository.findByUsername(username);

        if(customerEntity.isEmpty()) {
            throw ServiceErrorException.CustomCustomerExceptionNotFound(username);
        }

        return customerEntity.get();
    }

    public CustomerEntity checkCustomerAuthen(String username) throws ServiceErrorException
    {
        CustomerEntity cust = GetCustomerProfileByUsername(username);
        boolean isAuthen = authenService.CheckUserAuthen(username);
        if (isAuthen == false) {
            throw ServiceErrorException.Pleaselogin();
        }
        return  cust;
    }





}
