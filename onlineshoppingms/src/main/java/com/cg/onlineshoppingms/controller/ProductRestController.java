package com.cg.onlineshoppingms.controller;

import com.cg.onlineshoppingms.productms.dto.AddProductRequest;
import com.cg.onlineshoppingms.productms.dto.ProductDetails;
import com.cg.onlineshoppingms.productms.entities.Product;
import com.cg.onlineshoppingms.productms.service.IProductService;
import com.cg.onlineshoppingms.productms.util.ProductUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController
{
	private static final Logger LOG= LoggerFactory.getLogger(ProductRestController.class);
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductUtil productUtil;

    /**
     * adds a new product in the database.
     * @param request
     * @return
     */
    @PostMapping(value = "/a/products/add")
    public ProductDetails addProduct(@RequestBody AddProductRequest request)
    {
    	LOG.info("inside addProduct method of  ProductRestController with input product name: "+request.getProductName()+" and price: "+request.getPrice());
        Product product = productService.add(request.getProductName(), request.getPrice());
        return productUtil.toDetail(product);
    }

    /**
     * finds a product by productId and returns it's details.
     * @param productId
     * @return
     */
    @GetMapping(value = "/c/products/byid/{id}")
    public ProductDetails findById(@PathVariable("id")Long productId)
    {
    	LOG.info("inside findById method of ProductRestController with product id :"+productId);
        Product product = productService.findById(productId);
        return productUtil.toDetail(product);
    }

}
