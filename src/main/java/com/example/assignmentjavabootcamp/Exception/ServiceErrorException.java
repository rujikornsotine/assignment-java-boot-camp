package com.example.assignmentjavabootcamp.Exception;

public class ServiceErrorException extends BaseException {
    public ServiceErrorException(String message, String errorcode) {
        super(message, errorcode);
    }

    public ServiceErrorException AuthenticationFail(){
        return new ServiceErrorException("authentication failed!","A0001");
    }

    public static ServiceErrorException CouponNotFoundorExpire(){
        return new ServiceErrorException("Coupon not found or expire.","CP001");
    }

    public static ServiceErrorException CustomCustomerExceptionNotFound(String user)
    {
        return  new ServiceErrorException(String.format("Username %s Not Found.",user),"C0001");
    }

    public static ServiceErrorException ParameterIsNotVaild(String pamram){
        return new ServiceErrorException(String.format("%s is not valid.",pamram),"PM001");
    }

    public static ServiceErrorException ProductNotFound(){
        return new ServiceErrorException("Product not found.","PM003");
    }

    public static ServiceErrorException AddressNotFound(){
        return new ServiceErrorException("Address not found.","PM006");
    }

    public static ServiceErrorException PaymentMethodNotFound(){
        return new ServiceErrorException("PaymentMethod not found.","PM004");
    }

    public static ServiceErrorException PaymentNotFound(){
        return new ServiceErrorException("Payment not found.","PM007");
    }

    public static ServiceErrorException PaymentInvalidFlow(){
        return new ServiceErrorException("Payment invalid flow.","PM005");
    }

    public static ServiceErrorException Pleaselogin(){
        return new ServiceErrorException("Please login.","PM007");
    }

    public static ServiceErrorException ProductinpurchaseNotFound(){
        return new ServiceErrorException("Product in purchase not found.","PHE001");
    }

    public static ServiceErrorException ProductDuplicate(){
        return new ServiceErrorException("Product in purchase duplicate.","PHE004");
    }

    public static ServiceErrorException PurchaseNotFound(){
        return new ServiceErrorException("Purchase not found.","PHE005");
    }

    public static ServiceErrorException PurchaseDoesNotContain(){
        return new ServiceErrorException("Purchase id does not contain.","PHE006");
    }

    public static ServiceErrorException listPurchaseisNullorEmpty(){
        return new ServiceErrorException("Purchases is null or empty.","PHE006");
    }
}
