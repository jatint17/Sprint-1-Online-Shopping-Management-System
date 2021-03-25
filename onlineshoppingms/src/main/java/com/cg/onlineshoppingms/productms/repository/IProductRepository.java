package com.cg.onlineshoppingms.productms.repository;

import com.cg.onlineshoppingms.productms.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
