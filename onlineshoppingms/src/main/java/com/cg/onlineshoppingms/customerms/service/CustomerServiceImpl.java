package com.cg.onlineshoppingms.customerms.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
	ICustomerRepository customerRepository;
	@Autowired
	IUserRepository userRepository;

	@Transactional
	@Override
	public Customer addCustomer(String username, String password) 
	{
		Set<String> roles = new HashSet<>();
		roles.add("customer");
		User user = new User(username, password, roles);
		userRepository.save(user);
		Customer customer = new Customer(0.0,user);
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long customerId) 
	{
		Optional<Customer> optional = customerRepository.findById(customerId);
		if(!optional.isPresent())
		{
			throw new RuntimeException("customer not found");
		}
		return optional.get();		
	}

	@Transactional
	@Override
	public Customer updateBalance(Long customerId, Double newBalance) 
	{
		Customer customer = findById(customerId);
		customer.setBalance(newBalance);
		return customerRepository.save(customer);
	}

}
