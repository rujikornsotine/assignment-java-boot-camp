package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.PaymentMethodEntity;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodEntity getPaymentMethodByID(String paymentmethodid) throws ServiceErrorException
    {
        Optional<PaymentMethodEntity> result = paymentMethodRepository.findByPaymentmethodid(paymentmethodid);

        if(result.isPresent() == false)
        {
            throw ServiceErrorException.PaymentMethodNotFound();
        }

        return result.get();
    }

    public List<PaymentMethodEntity> ListPaymentMethod() throws ServiceErrorException {

        List<PaymentMethodEntity> PaymentMethods = paymentMethodRepository.findAll();

        if(PaymentMethods == null || PaymentMethods.size() <= 0)
        {
            throw ServiceErrorException.PaymentMethodNotFound();
        }

        return PaymentMethods;
    }

}
