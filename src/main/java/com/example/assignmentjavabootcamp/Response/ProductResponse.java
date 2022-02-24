package com.example.assignmentjavabootcamp.Response;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String productid;
    private String productname;
    private String description;
    private String band;
    private String size;
    private String color;
    private int amount;
    private double price;
    private double discount;
    private String discountexpire;
    private String sex;
    private String type;
    private String imgpath1;
    private String imgpath2;
    private String imgpath3;
    private String imgpath4;
    private String imgpath5;

    public ProductResponse() {
    }

    public ProductResponse(ProductEntity productEntityList){
        this.productid = productEntityList.getProductid();
        this.productname = productEntityList.getProductname();
        this.description = productEntityList.getDescription();
        this.band = productEntityList.getBand();
        this.size = productEntityList.getSize();
        this.color = productEntityList.getColor();
        this.amount = productEntityList.getAmount();
        this.price = productEntityList.getPrice();
        this.discount = productEntityList.getDiscount();
        this.discountexpire = productEntityList.getDiscountexpire();
        this.sex = productEntityList.getSex();
        this.type = productEntityList.getType();
        this.imgpath1 = productEntityList.getImgpath1();
        this.imgpath2 = productEntityList.getImgpath2();
        this.imgpath3 = productEntityList.getImgpath3();
        this.imgpath4 = productEntityList.getImgpath4();
        this.imgpath5 = productEntityList.getImgpath5();
    }
}

