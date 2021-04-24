package com.cg.onlineshoppingms.customerms.repository;

import com.cg.onlineshoppingms.userms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshoppingms.customerms.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long>
{

    Customer findCustomerByUser(User user);

}
