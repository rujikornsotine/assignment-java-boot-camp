package com.example.assignmentjavabootcamp.Response;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import lombok.Data;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseList {
    private List<ProductResponse>  productResponseList;

    public ProductResponseList(List<ProductEntity> productEntityList){

        productResponseList = new ArrayList<ProductResponse>();
        for (var product:
                productEntityList) {
            if(product != null){
                ProductResponse item = new ProductResponse(product);
                productResponseList.add(item);
            }
        }
    }
}
