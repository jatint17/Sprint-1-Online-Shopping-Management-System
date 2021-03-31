package com.cg.onlineshoppingms.customerms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.cg.onlineshoppingms.userms.entity.User;

@Entity
public class Customer 
{
	@GeneratedValue
	@Id
	private Long customerId;
	private Double balance;
	@OneToOne
	private User user;
	
	public Customer() {}
	public Customer(Double balance, User user)
	{
		this.balance=balance;
		this.user=user;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}