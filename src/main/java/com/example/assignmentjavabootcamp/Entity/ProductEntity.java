package com.example.assignmentjavabootcamp.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
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
    private boolean recommend;

    public ProductEntity() {
    }

    public ProductEntity(String product_id, String product_name, String description, String band, String size, String color,
                         int amount, double price, double discount, String discount_expire, String sex, String type, String img_path_1,
                         String img_path_2, String img_path_3, String img_path_4, String img_path_5, boolean recommend)
    {
        this.productid = product_id;
        this.productname = product_name;
        this.description = description;
        this.band = band;
        this.size = size;
        this.color = color;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.discountexpire = discount_expire;
        this.sex = sex;
        this.type = type;
        this.imgpath1 = img_path_1;
        this.imgpath2 = img_path_2;
        this.imgpath3 = img_path_3;
        this.imgpath4 = img_path_4;
        this.imgpath5 = img_path_5;
        this.recommend = recommend;
    }
}
