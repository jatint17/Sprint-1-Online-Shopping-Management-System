package com.cg.onlineshoppingms.productms.service;

import com.cg.onlineshoppingms.productms.entities.Product;
import com.cg.onlineshoppingms.productms.exceptions.InvalidIdException;
import com.cg.onlineshoppingms.productms.exceptions.InvalidNameException;
import com.cg.onlineshoppingms.productms.exceptions.ProductNotFoundException;
import com.cg.onlineshoppingms.productms.repository.IProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService
{
	private static final Logger Log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private IProductRepository productRepository;

    /**
     * adds a new product in the database.
     * @param name
     * @param price
     * @return
     */
    @Transactional
    @Override
    public Product add(String name, double price)
    {
        validateName(name);
        Log.debug("inside add method in product service with product name: "+name+" and price: "+price);
        Product product = new Product(name, price);
        return productRepository.save(product);
    }

    /**
     * finds and returns a product by productId
     * @param id
     * @return
     */
    @Override
    public Product findById(long id)
    {
        validateId(id);
        Log.debug("inside add method in product service with product id: "+id);
        Optional<Product> optional = productRepository.findById(id);
        if(!optional.isPresent())
        {
            throw new ProductNotFoundException("Product for this id does not exist");
        }
        return optional.get();
    }

    /**
     * validates and throws InvalidIdException if the id is negative.
     * @param id
     */
    public void validateId(long id)
    {
        if(id<0)
        {
            throw new InvalidIdException("Invalid Id");
        }
    }

    /**
     * validates and throws InvalidNameException if the name is null or empty.
     * @param name
     */
    public void validateName(String name)
    {
        if (name == null || name.isEmpty() || name.trim().isEmpty())
        {
            throw new InvalidNameException("Username cannot be null or empty");
        }
    }
    
   
}
