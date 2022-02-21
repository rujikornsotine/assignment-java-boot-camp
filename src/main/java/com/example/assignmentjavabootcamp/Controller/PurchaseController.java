package com.example.assignmentjavabootcamp.Controller;

import com.example.assignmentjavabootcamp.Entity.PurchaseEntity;
import com.example.assignmentjavabootcamp.Exception.PurchaseException;
import com.example.assignmentjavabootcamp.Request.ItemProductRequest;
import com.example.assignmentjavabootcamp.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/additemproduct")
    public ResponseEntity AddItemProduct(@RequestBody ItemProductRequest request) throws PurchaseException {
        purchaseService.AddPurchase(request.getUsername(),request.getProductid(),request.getAmount());
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PurchaseEntity>> GetAll(){
       return ResponseEntity.ok(purchaseService.GetAll()) ;
    }

    @GetMapping("/listbyusername/{username}")
    public ResponseEntity<List<PurchaseEntity>> ListByUsername(@PathVariable String username) throws PurchaseException {
        return ResponseEntity.ok(purchaseService.ListByUsername(username)) ;
    }

    @PostMapping("/edititemproduct")
    public ResponseEntity EdittemProduct(@RequestBody ItemProductRequest request) throws PurchaseException {
        purchaseService.EditPurchase(request.getUsername(),request.getProductid(),request.getAmount(),request.getId());
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/deleteitemproduct")
    public ResponseEntity DeletetemProduct(@RequestBody ItemProductRequest request) throws PurchaseException {
        purchaseService.DeletePurchase(request.getUsername(),request.getProductid(),request.getId());
        return  ResponseEntity.ok().build();
    }



}
