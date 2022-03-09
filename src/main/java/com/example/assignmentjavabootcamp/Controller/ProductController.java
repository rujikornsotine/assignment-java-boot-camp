package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.ProductEntity;
import com.example.assignmentjavabootcamp.Exception.ProductException;
import com.example.assignmentjavabootcamp.Exception.ServiceErrorException;
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

    @GetMapping("/listbyrecommend")
    public ResponseEntity<ProductResponseList> ListproductByRecommend() throws ServiceErrorException {

        Optional<List<ProductEntity>> productList = productService.ListProductByRecommend();
        ProductResponseList _productList = new ProductResponseList(productList.orElseThrow()) ;
        return ResponseEntity.ok(_productList);
    }

    @GetMapping("/listbykeyword/{keyword}")
    public ResponseEntity<ProductResponseList> ListproductByKeyword(@PathVariable String keyword) throws ServiceErrorException {

        List<ProductEntity> productList = productService.ListProductByKeyword(keyword);
        ProductResponseList _productList = new ProductResponseList(productList) ;
        return ResponseEntity.ok(_productList);
    }

    @GetMapping("/getbyid/{productid}")
    public ResponseEntity<ProductResponse> GetProductByID(@PathVariable String productid) throws ServiceErrorException {

        ProductEntity productEntity = productService.GetProductByID(productid);
        ProductResponse _product = new ProductResponse(productEntity);
        return ResponseEntity.ok(_product);
    }

    @GetMapping("/listbysellproductgroup/{band}/{productid}")
    public ResponseEntity<ProductResponseList> ListProductBysellProductGroup(@PathVariable String band,@PathVariable String productid) throws ServiceErrorException {
        Optional<List<ProductEntity>> productList = productService.ListProductBysellProductGroup(band,productid);
        ProductResponseList _products = new ProductResponseList(productList.orElseThrow());
        return ResponseEntity.ok(_products);
    }

}
