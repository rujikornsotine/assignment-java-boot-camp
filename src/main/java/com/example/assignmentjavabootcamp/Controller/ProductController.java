package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Exception.ProductException;
import com.example.assignmentjavabootcamp.Response.ProductResponse;
import com.example.assignmentjavabootcamp.Response.ProductResponseList;
import com.example.assignmentjavabootcamp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listproductbyrecommend")
    public ResponseEntity<ProductResponseList> ListproductByRecommend() throws ProductException {

        Optional<List<ProductEntity>> productList = productService.ListProductByRecommend();
        ProductResponseList _productList = new ProductResponseList(productList.orElseThrow()) ;
        return ResponseEntity.ok(_productList);
    }

    @GetMapping("/listproductbykeyword/{keyword}")
    public ResponseEntity<ProductResponseList> ListproductByKeyword(@PathVariable String keyword) throws ProductException {

        Optional<List<ProductEntity>> productList = productService.ListProductByKeyword(keyword);
        ProductResponseList _productList = new ProductResponseList(productList.orElseThrow()) ;
        return ResponseEntity.ok(_productList);
    }

    @GetMapping("/getproductbyid/{productid}")
    public ResponseEntity<ProductResponse> GetProductByID(@PathVariable String productid) throws ProductException {

        Optional<ProductEntity> productEntity = productService.GetProductByID(productid);
        ProductResponse _product = new ProductResponse(productEntity.orElseThrow());
        return ResponseEntity.ok(_product);
    }

    @GetMapping("/listproductbysellproductgroup/{band}/{productid}")
    public ResponseEntity<ProductResponseList> ListProductBysellProductGroup(@PathVariable String band,@PathVariable String productid) throws ProductException {
        Optional<List<ProductEntity>> productList = productService.ListProductBysellProductGroup(band,productid);
        ProductResponseList _products = new ProductResponseList(productList.orElseThrow());
        return ResponseEntity.ok(_products);
    }

}
