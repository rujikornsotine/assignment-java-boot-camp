package com.example.assignmentjavabootcamp.Entity;

import com.example.assignmentjavabootcamp.Service.PaymentService;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String refnumber;
    private String username;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private PaymentDetailEntity paymentDetail;

   @OneToMany(mappedBy = "payment",cascade = CascadeType.ALL)
   private List<ItemPayment> itemPayments;

   @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
   private AddressPayment addressEntity;



    public PaymentEntity() {
        //this.addressEntity = new AddressEntity();
        //this.purchaseEntityList = new ArrayList<PurchaseEntity>();

    }
}
