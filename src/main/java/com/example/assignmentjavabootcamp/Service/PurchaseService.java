package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.PurchaseException;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import com.example.assignmentjavabootcamp.Repository.ProductRepository;
import com.example.assignmentjavabootcamp.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void AddPurchase(String username,String productid,int amount) throws PurchaseException{
        AddorUpdatePurchase(username,productid,amount,0);
    }

    public List<PurchaseEntity> ListByUsername(String uername) throws PurchaseException {
        List<PurchaseEntity> resultPurchaseList = null;
        Optional<List<PurchaseEntity>> purchaseList = purchaseRepository.findByUsername(uername);

        if(purchaseList.isEmpty()){
            throw PurchaseException.PurchaseNotFound();
        }

        if(purchaseList.isPresent()){
            resultPurchaseList = purchaseList.orElseThrow();
        }

        return  resultPurchaseList;
    }

    public void EditPurchase (String username,String productid,int amount,int id) throws PurchaseException{

        if(id <= 0)
        {
            throw PurchaseException.ParameterIsNotVaild("ID");
        }

        AddorUpdatePurchase(username,productid,amount,id);

    }

    private void AddorUpdatePurchase(String username,String productid,int amount,int id) throws PurchaseException{
        PurchaseEntity purchase = new PurchaseEntity();
        double actual_price = 0.00;
        double price_summary = 0.00;

        if(username.isEmpty()){
            throw PurchaseException.ParameterIsNotVaild("Username");
        }

        if(productid.isEmpty()){
            throw PurchaseException.ParameterIsNotVaild("Productid");
        }

        if(amount<= 0){
            throw PurchaseException.ParameterIsNotVaild("amount");
        }

        Optional<CustomerEntity> customer = customerRepository.findByUsername(username);

        if(customer.isEmpty()){
            throw PurchaseException.CustomerNotFound();
        }


        Optional<ProductEntity> product =  productRepository.findByProductid(productid);

        if(product.isEmpty()){
            throw PurchaseException.ProductNotFound();
        }


        Optional<PurchaseEntity> resultCheck = purchaseRepository.findByUsernameAndProductid(username,productid);

        if(resultCheck.isPresent() && id <= 0){
            throw PurchaseException.ProductDuplicate();
        }

        if(resultCheck.isEmpty() && id > 0){
            throw PurchaseException.PurchaseNotFound();
        }

        if(resultCheck.isPresent() && id > 0){

            if(resultCheck.get().getId() != id){
                throw PurchaseException.PurchaseDoesNotContain();
            }
        }


        if(product.isPresent()){
            ProductEntity item =  product.get();
            actual_price = item.getPrice() - item.getDiscount();
            price_summary = (actual_price * amount);

            if(id > 0){
                purchase.setId(resultCheck.get().getId());
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
    }

    public List<PurchaseEntity> GetAll() {
      return  purchaseRepository.findAll();
    }

    public void DeletePurchase(String username,String productid,int id) throws PurchaseException{
        if(username.isEmpty()){
            throw PurchaseException.ParameterIsNotVaild("Username");
        }

        if(productid.isEmpty()){
            throw PurchaseException.ParameterIsNotVaild("Productid");
        }

        if(id <= 0){
            throw PurchaseException.ParameterIsNotVaild("amount");
        }

        Optional<PurchaseEntity> resultCheck = purchaseRepository.findByUsernameAndProductid(username,productid);

        if(resultCheck.isEmpty()){
            throw PurchaseException.PurchaseNotFound();
        }


        if(resultCheck.isPresent() && id > 0){

            if(resultCheck.get().getId() == id){
                purchaseRepository.deleteById(resultCheck.get().getId());
            }
        }
    }
}
