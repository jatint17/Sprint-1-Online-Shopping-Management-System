package com.cg.onlineshoppingms.customerms.dto;

public class UpdateBalanceRequest 
{
	private Long customerId;
	private Double newBalance;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
	}
	
	
}
