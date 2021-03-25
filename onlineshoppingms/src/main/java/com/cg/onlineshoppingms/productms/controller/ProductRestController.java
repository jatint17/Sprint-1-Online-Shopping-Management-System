package com.cg.onlineshoppingms.productms.controller;

import com.cg.onlineshoppingms.productms.dto.AddProductRequest;
import com.cg.onlineshoppingms.productms.dto.ProductDetails;
import com.cg.onlineshoppingms.productms.dto.UpdatePriceRequest;
import com.cg.onlineshoppingms.productms.entities.Product;
import com.cg.onlineshoppingms.productms.service.IProductService;
import com.cg.onlineshoppingms.productms.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductRestController
{
    @Autowired
    IProductService productService;
    @Autowired
    ProductUtil productUtil;

    @PostMapping(value = "/add")
    public ProductDetails addProduct(@RequestBody AddProductRequest request)
    {
        Product product = productService.add(request.getProductName(), request.getPrice());
        return productUtil.toDetail(product);
    }

    @GetMapping(value = "/byid/{id}")
    public ProductDetails findById(@PathVariable("id")Long productId)
    {
        Product product = productService.findById(productId);
        return productUtil.toDetail(product);
    }

    @PutMapping(value = "/updateprice")
    public ProductDetails updatePrice(@RequestBody UpdatePriceRequest request)
    {
        Product product = productService.updatePrice(request.getProductId(), request.getNewPrice());
        return productUtil.toDetail(product);
    }

}
