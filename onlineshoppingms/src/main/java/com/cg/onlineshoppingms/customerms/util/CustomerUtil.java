package com.cg.onlineshoppingms.customerms.util;

import com.cg.onlineshoppingms.customerms.dto.CustomerDetails;
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
    public CustomerDetails toCustomerDetails(Customer customer)
    {
        CustomerDetails customerDetails=new CustomerDetails(customer.getCustomerId(), customer.getUser().getUsername());
        return customerDetails;
    }

}
