package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.AddressEntity;
import com.example.assignmentjavabootcamp.Entity.AddressPayment;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.AddressPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressPaymentRepository addressPaymentRepository;

   /* public void addAddressPayment(AddressEntity address,String username,String refnumber)
    {
        addressPaymentRepository.save(new AddressPayment(address,username,refnumber));
    }

    public AddressPayment getByByUsernameAndPaymentRefNumber(String username,String refnumber) throws ServiceErrorException {
       Optional<AddressPayment> addressPayment = addressPaymentRepository.findByUsernameAndPaymentrefnumber(username,refnumber);

       if(addressPayment.isPresent() == false){
           throw ServiceErrorException.AddressNotFound();
       }
       return  addressPayment.get();
    }*/
}
