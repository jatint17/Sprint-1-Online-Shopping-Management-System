package com.cg.onlineshoppingms.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cg.onlineshoppingms.customerms.dto.CustomerDetails;
import com.cg.onlineshoppingms.customerms.entity.Customer;
import com.cg.onlineshoppingms.customerms.service.ICustomerService;
import com.cg.onlineshoppingms.customerms.util.CustomerUtil;
import com.cg.onlineshoppingms.userms.dto.AddRequest;

@RestController
public class CustomerRestController 
{

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private CustomerUtil customerUtil;
	
	public static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);
	
	/**
     * saves a new unique customer in the database.
     * @param request
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/p/customers/add")
    public CustomerDetails addCustomer(@RequestBody AddRequest request)
    {
    	log.info("inside addCustomer rest controller "+request.getUsername()+" "+request.getPassword());
    	
        Customer customer = customerService.addCustomer(request.getUsername(), request.getPassword());
        CustomerDetails response = customerUtil.toCustomerDetails(customer);
        return response;
    }
    
    /**
     * finds a customer in database by id and returns it's details
     * @param customerId
     * @return UserDetailsResponse
     */
    @GetMapping("/c/customers/byid/{id}")
    public CustomerDetails findById(@PathVariable("id") Long customerId)
    {
    	log.info("inside findbyId rest controller "+customerId);
    	
        Customer customer = customerService.findById(customerId);
        return customerUtil.toCustomerDetails(customer);
    }   

    /**
     *
     * @param username
     * @return
     */
    @GetMapping("/c/customers/byusername/{username}")
    public CustomerDetails findByUsername(@PathVariable String username)
    {
    	log.info("inside findByUsername rest contoller "+username);
    	
    	Customer customer= customerService.findByUsername(username);
        return customerUtil.toCustomerDetails(customer);
    }

}
