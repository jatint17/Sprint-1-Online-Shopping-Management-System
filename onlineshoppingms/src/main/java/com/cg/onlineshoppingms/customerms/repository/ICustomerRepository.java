package com.cg.onlineshoppingms.customerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshoppingms.customerms.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long>
{

}
