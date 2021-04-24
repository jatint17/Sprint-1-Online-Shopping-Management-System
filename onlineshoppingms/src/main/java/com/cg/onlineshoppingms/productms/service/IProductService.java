package com.cg.onlineshoppingms.productms.service;

import com.cg.onlineshoppingms.productms.entities.Product;

public interface IProductService
{
    Product add(String name, double price);
    Product findById(long id);
}
