package com.cg.onlineshoppingms.customerms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineshoppingms.customerms.dto.CustomerDetailsResponse;
import com.cg.onlineshoppingms.customerms.dto.UpdateBalanceRequest;
import com.cg.onlineshoppingms.customerms.entity.Customer;
import com.cg.onlineshoppingms.customerms.service.ICustomerService;
import com.cg.onlineshoppingms.customerms.util.CustomerUtil;
import com.cg.onlineshoppingms.userms.dto.AddRequest;

@RestController
public class CustomerRestController 
{

	@Autowired
	ICustomerService customerService;
	@Autowired
	CustomerUtil customerUtil;
	
	/**
     * saves a new unique customer in the database.
     * @param request
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/p/customers/add")
    public CustomerDetailsResponse addCustomer(@RequestBody AddRequest request)
    {
        Customer customer = customerService.addCustomer(request.getUsername(), request.getPassword());
        CustomerDetailsResponse response = customerUtil.toCustomerDetails(customer);
        return response;
    }
    
    /**
     * finds a customer in database by id and returns it's details
     * @param request
     * @return UserDetailsResponse
     */
    @GetMapping("/c/customers/byid/{id}")
    public CustomerDetailsResponse findById(@PathVariable("id") Long customerId)
    {
        Customer customer = customerService.findById(customerId);
        return customerUtil.toCustomerDetails(customer);
    }
    
    /**
     * updates the balance of an existing customer in the database
     * @param request
     * @return UserDetailsResponse
     */
    @PutMapping("/a/customers/updatebalance")
    public CustomerDetailsResponse updateBalance(@RequestBody UpdateBalanceRequest request)
    {
        Customer customer = customerService.updateBalance(request.getCustomerId(), request.getNewBalance());
        CustomerDetailsResponse response = customerUtil.toCustomerDetails(customer);
        return response;
    }
}
