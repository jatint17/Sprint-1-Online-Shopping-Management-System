package com.cg.onlineshoppingms.customerms.util;

import com.cg.onlineshoppingms.customerms.dto.CustomerDetailsResponse;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.customerms.entity.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtil
{

    /**
     * utility method to convert the entity object, User, to it's details class, UserDetailsResponse.
     * @param customer
     * @return
     */
    public CustomerDetailsResponse toCustomerDetails(Customer customer)
    {
        CustomerDetailsResponse customerDetails=new CustomerDetailsResponse(customer.getCustomerId(), customer.getUser().getUsername(), customer.getBalance());
        return customerDetails;
    }

}
