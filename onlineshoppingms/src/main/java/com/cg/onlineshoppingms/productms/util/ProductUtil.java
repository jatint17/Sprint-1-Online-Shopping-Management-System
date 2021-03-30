package com.cg.onlineshoppingms.productms.util;

import com.cg.onlineshoppingms.productms.dto.ProductDetails;
import com.cg.onlineshoppingms.productms.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductUtil
{

    /**
     * utility method to convert the entity object, Product, to it's details class, ProductDetails.
     * @param product
     * @return
     */
    public ProductDetails toDetail(Product product)
    {
        ProductDetails details = new ProductDetails(product.getId(), product.getName(), product.getPrice());
        return details;
    }
}
