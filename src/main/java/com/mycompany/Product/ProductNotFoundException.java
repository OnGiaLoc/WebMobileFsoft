package com.mycompany.Product;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String message){
        super(message);
    }
}
