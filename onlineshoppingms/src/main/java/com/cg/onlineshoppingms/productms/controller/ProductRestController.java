package com.cg.onlineshoppingms.productms.controller;

import com.cg.onlineshoppingms.productms.dto.AddProductRequest;
import com.cg.onlineshoppingms.productms.dto.ProductDetails;
import com.cg.onlineshoppingms.productms.dto.UpdatePriceRequest;
import com.cg.onlineshoppingms.productms.entities.Product;
import com.cg.onlineshoppingms.productms.service.IProductService;
import com.cg.onlineshoppingms.productms.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController
{
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductUtil productUtil;

    @PostMapping(value = "/a/products/add")
    public ProductDetails addProduct(@RequestBody AddProductRequest request)
    {
        Product product = productService.add(request.getProductName(), request.getPrice());
        return productUtil.toDetail(product);
    }

    @GetMapping(value = "/c/products/byid/{id}")
    public ProductDetails findById(@PathVariable("id")Long productId)
    {
        Product product = productService.findById(productId);
        return productUtil.toDetail(product);
    }

    @PutMapping(value = "/a/products/updateprice")
    public ProductDetails updatePrice(@RequestBody UpdatePriceRequest request)
    {
        Product product = productService.updatePrice(request.getProductId(), request.getNewPrice());
        return productUtil.toDetail(product);
    }

}
