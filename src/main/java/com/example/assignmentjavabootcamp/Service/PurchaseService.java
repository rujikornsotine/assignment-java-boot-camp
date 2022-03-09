package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.ProductException;
import com.example.assignmentjavabootcamp.Exception.PurchaseException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import com.example.assignmentjavabootcamp.Repository.ProductRepository;
import com.example.assignmentjavabootcamp.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerService customerService;


    public boolean AddPurchase(String username,String productid,int amount) throws ServiceErrorException {
       return  AddorUpdatePurchase(username,productid,amount,0);
    }

    public List<PurchaseEntity> ListByUsername(String uername) throws ServiceErrorException {
        List<PurchaseEntity> resultPurchaseList = null;
        Optional<List<PurchaseEntity>> purchaseList = purchaseRepository.findByUsername(uername);

        if(purchaseList.isEmpty()){
            throw ServiceErrorException.PurchaseNotFound();
        }

        if(purchaseList.isPresent()){
            resultPurchaseList = purchaseList.orElseThrow();
        }

        return  resultPurchaseList;
    }

    public boolean EditPurchase (String username,String productid,int amount,int id) throws ServiceErrorException{

        if(id <= 0)
        {
            throw ServiceErrorException.ParameterIsNotVaild("ID");
        }

       return AddorUpdatePurchase(username,productid,amount,id);

    }


    private boolean AddorUpdatePurchase(String username,String productid,int amount,int id) throws ServiceErrorException {
        PurchaseEntity purchase = new PurchaseEntity();
        boolean result = true;
        double actual_price = 0.00;
        double price_summary = 0.00;

        if(username.isEmpty()){
            throw ServiceErrorException.ParameterIsNotVaild("Username");
        }

        if(productid.isEmpty()){
            throw ServiceErrorException.ParameterIsNotVaild("Productid");
        }

        if(amount<= 0){
            throw ServiceErrorException.ParameterIsNotVaild("amount");
        }

       // CustomerService customerService = new CustomerService();
        CustomerEntity customer = customerService.GetCustomerProfileByUsername(username);

        ProductService productService = new ProductService();
        ProductEntity product = productService.GetProductByID(productid);


        PurchaseEntity resultCheck = getPurchaseByUsernameAndProductID(username,productid);

        if(resultCheck != null && id <= 0){
            throw ServiceErrorException.ProductDuplicate();
        }

        if(resultCheck == null && id > 0){
            throw ServiceErrorException.PurchaseNotFound();
        }

        if(resultCheck != null && id > 0){

            if(resultCheck.getId() != id){
                throw ServiceErrorException.PurchaseDoesNotContain();
            }
        }


        if(product != null){
            ProductEntity item =  product;
            actual_price = item.getPrice() - item.getDiscount();
            price_summary = (actual_price * amount);

            if(id > 0){
                purchase.setId(resultCheck.getId());
            }

            purchase.setUsername(username);
            purchase.setProductid(productid);
            purchase.setAmount(amount);
            purchase.setPrice(item.getPrice());
            purchase.setActual_price(actual_price);
            purchase.setDiscount(item.getDiscount());
            purchase.setSum_price(price_summary);
            purchaseRepository.save(purchase);

        }

        return  result;
    }

    public PurchaseEntity getPurchaseByUsernameAndProductID(String username,String productid) throws ServiceErrorException{

        Optional<PurchaseEntity> resultCheck = purchaseRepository.findByUsernameAndProductid(username,productid);

        if(resultCheck.isPresent() == false)
        {
            throw ServiceErrorException.PurchaseNotFound();
        }

        return  resultCheck.get();

    }

    public List<PurchaseEntity> GetAll() {
      return  purchaseRepository.findAll();
    }

    public void DeletePurchase(String username,String productid,int id) throws ServiceErrorException{
        if(username.isEmpty()){
            throw ServiceErrorException.ParameterIsNotVaild("Username");
        }

        if(productid.isEmpty()){
            throw ServiceErrorException.ParameterIsNotVaild("Productid");
        }

        if(id <= 0){
            throw ServiceErrorException.ParameterIsNotVaild("amount");
        }

        Optional<PurchaseEntity> resultCheck = purchaseRepository.findByUsernameAndProductid(username,productid);

        if(resultCheck.isEmpty()){
            throw ServiceErrorException.PurchaseNotFound();
        }


        if(resultCheck.isPresent() && id > 0){

            if(resultCheck.get().getId() == id){
                purchaseRepository.deleteById(resultCheck.get().getId());
            }
        }
    }
}
