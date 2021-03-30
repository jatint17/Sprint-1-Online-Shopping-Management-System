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

    /**
     * adds a new product in the database.
     * @param request
     * @return
     */
    @PostMapping(value = "/a/products/add")
    public ProductDetails addProduct(@RequestBody AddProductRequest request)
    {
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
        Product product = productService.findById(productId);
        return productUtil.toDetail(product);
    }

    /**
     * updates the price of a product by Id in the database.
     * @param request
     * @return
     */
    @PutMapping(value = "/a/products/updateprice")
    public ProductDetails updatePrice(@RequestBody UpdatePriceRequest request)
    {
        Product product = productService.updatePrice(request.getProductId(), request.getNewPrice());
        return productUtil.toDetail(product);
    }

}
