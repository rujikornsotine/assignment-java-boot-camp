package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Exception.ProductException;
import com.example.assignmentjavabootcamp.Repository.PaymentRepository;
import com.example.assignmentjavabootcamp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Optional<List<ProductEntity>> ListProductByRecommend() throws ProductException {
        Optional<List<ProductEntity>> productlist = productRepository.findByRecommendTrue();

        if(productlist.isEmpty()){
            throw ProductException.ProductNotFound();
        }

        return  productlist;
    }

    public Optional<List<ProductEntity>> ListProductByKeyword(String keyword) throws ProductException{
        Optional<List<ProductEntity>> productlist = productRepository.findByProductnameContainingIgnoreCase(keyword);
        if(productlist.isEmpty()){
            throw ProductException.ProductNotFound();
        }
        return  productlist;
    }

    public Optional<ProductEntity> GetProductByID(String productid) throws ProductException{
        Optional<ProductEntity> product = productRepository.findByProductid(productid);
        if(product.isEmpty()){
            throw ProductException.ProductNotFound();
        }
        return  product;
    }

    public Optional<List<ProductEntity>> ListProductBysellProductGroup(String band,String productid) throws ProductException{
        Optional<List<ProductEntity>> productlist = productRepository.findByBandIgnoreCaseAndProductidNot(band,productid);
        if(productlist.isEmpty()){
            throw ProductException.ProductNotFound();
        }
        return  productlist;
    }


}
