package com.cg.onlineshoppingms.productms.service;

import com.cg.onlineshoppingms.productms.entities.Product;
import com.cg.onlineshoppingms.productms.exceptions.InvalidIdException;
import com.cg.onlineshoppingms.productms.exceptions.InvalidNameException;
import com.cg.onlineshoppingms.productms.exceptions.ProductNotFoundException;
import com.cg.onlineshoppingms.productms.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private IProductRepository productRepository;

    @Transactional
    @Override
    public Product add(String name, double price)
    {
        validateName(name);
        Product product = new Product(name,price);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updatePrice(long productId, double price)
    {
        Product product = findById(productId);
        product.setPrice(price);
        return productRepository.save(product);
    }

    @Override
    public Product findById(long id)
    {
        validateId(id);
        Optional<Product> optional = productRepository.findById(id);
        if(!optional.isPresent())
        {
            throw new ProductNotFoundException("Product for this id does not exist");
        }
        return optional.get();
    }

    public void validateId(long id)
    {
        if(id<0)
        {
            throw new InvalidIdException("Invalid Id");
        }
    }

    public void validateName(String name)
    {
        if (name == null || name.isEmpty() || name.trim().isEmpty())
        {
            throw new InvalidNameException("Username cannot be null or empty");
        }
    }
}
