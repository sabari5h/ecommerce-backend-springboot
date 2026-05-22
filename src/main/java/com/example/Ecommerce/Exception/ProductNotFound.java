package com.example.Ecommerce.Exception;

public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String message) {
        super(message);
    }
}
