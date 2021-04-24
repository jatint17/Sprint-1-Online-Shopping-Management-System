package com.cg.onlineshoppingms.customerms.service;

import com.cg.onlineshoppingms.customerms.entity.Customer;

public interface ICustomerService
{
    Customer addCustomer(String username, String password);
    Customer findById(Long customerId);
    Customer findByUsername(String username);
}