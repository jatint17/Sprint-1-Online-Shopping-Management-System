package com.cg.onlineshoppingms.customerms.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.onlineshoppingms.customerms.entity.Customer;
import com.cg.onlineshoppingms.customerms.repository.ICustomerRepository;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.repository.IUserRepository;

@Service
public class CustomerServiceImpl implements ICustomerService
{
	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private IUserRepository userRepository;
	
    public static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);


	@Transactional
	@Override
	public Customer addCustomer(String username, String password) 
	{
		log.info("inside addCustomer service"+username);
		
		Set<String> roles = new HashSet<>();
		roles.add("customer");
		User user = new User(username, password, roles);
		userRepository.save(user);
		Customer customer = new Customer(user);
		return customerRepository.save(customer);
	}


	@Override
	public Customer findById(Long customerId) 
	{
		log.info("inside findById service"+customerId);

		
		Optional<Customer> optional = customerRepository.findById(customerId);
		if(!optional.isPresent())
		{
			throw new RuntimeException("customer not found");
		}
		return optional.get();		
	}

	@Override
	public Customer findByUsername(String username)
	{
		log.info("inside findByUsername service"+username);

		User user=userRepository.findUserByUsername(username);
	    Customer customer=customerRepository.findCustomerByUser(user);
	    return customer;
	}

}
