package com.example.assignmentjavabootcamp.Exception;

public class PurchaseException extends BaseException{
    public PurchaseException(String message, String errorcode) {
        super(message, errorcode);
    }


    public static PurchaseException ProductNotFound(){
        return new PurchaseException("Product in purchase not found.","PHE001");
    }

    public static PurchaseException CustomerNotFound(){
        return new PurchaseException("Customer not found.","PHE002");
    }

    public static PurchaseException ParameterIsNotVaild(String pamram){
        return new PurchaseException(String.format("%s is not valid.",pamram),"PHE003");
    }

    public static PurchaseException ProductDuplicate(){
        return new PurchaseException("Product in purchase duplicate.","PHE004");
    }

    public static PurchaseException PurchaseNotFound(){
        return new PurchaseException("Purchase not found.","PHE005");
    }

    public static PurchaseException PurchaseDoesNotContain(){
        return new PurchaseException("Purchase id does not contain.","PHE006");
    }


}
