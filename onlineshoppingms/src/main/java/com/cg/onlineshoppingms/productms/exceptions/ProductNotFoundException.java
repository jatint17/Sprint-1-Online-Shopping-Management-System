package com.cg.onlineshoppingms.productms.exceptions;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException(String msg)
    {
        super(msg);
    }
}
