package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.ItemPayment;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.ItemPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemPaymentRepository itemPaymentRepository;

    /*
    public void addItemPayment(List<PurchaseEntity> purchaseList,String username,String refnumber) throws ServiceErrorException {

        if(purchaseList == null && purchaseList.size() <= 0){
            throw ServiceErrorException.listPurchaseisNullorEmpty();
        }

        for (var item : purchaseList) {
            itemPaymentRepository.save(new ItemPayment(item,username,refnumber));
        }
    }


    public List<ItemPayment> listByUsernameAndRefPaymentNumber(String username,String refnumber) throws ServiceErrorException {

       Optional<List<ItemPayment>> items = itemPaymentRepository.findByUsernameAndPaymentrefnumber(username,refnumber);

       if (items.isPresent() == false || items.isEmpty())
       {
           throw ServiceErrorException.listPurchaseisNullorEmpty();
       }

       return  items.get();
    }
     */
}
