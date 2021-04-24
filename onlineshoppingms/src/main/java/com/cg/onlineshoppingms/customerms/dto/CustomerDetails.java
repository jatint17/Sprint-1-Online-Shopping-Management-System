package com.cg.onlineshoppingms.customerms.dto;

public class CustomerDetails {

    private Long customerId;
    private String username;

    public CustomerDetails(){}
    public CustomerDetails(Long customerId, String username)
    {
        this.customerId = customerId;
        this.username = username;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
