package com.cg.onlineshoppingms;

import java.util.HashSet;
import java.util.Set;
import com.cg.onlineshoppingms.adminms.service.IAdminService;
import com.cg.onlineshoppingms.customerms.service.ICustomerService;
import com.cg.onlineshoppingms.productms.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingApplication
{

    public static final Logger log = LoggerFactory.getLogger(ShoppingApplication.class);

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingApplication.class, args);
        IAdminService adminService = context.getBean(IAdminService.class);
        ICustomerService customerService = context.getBean(ICustomerService.class);
        IProductService productService = context.getBean(IProductService.class);
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        adminService.add("admin", "admin");
        customerService.addCustomer("customer","custpass");
        productService.add("Nike",2300.0);

        log.info("-------started------");

    }

}
