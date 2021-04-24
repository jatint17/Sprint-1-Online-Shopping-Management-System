package com.cg.onlineshoppingms;

import java.util.HashSet;
import java.util.Set;

import com.cg.onlineshoppingms.adminms.service.IAdminService;
import com.cg.onlineshoppingms.customerms.service.ICustomerService;
import com.cg.onlineshoppingms.productms.service.IProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;

@SpringBootApplication
public class ShoppingApplication
{
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
        System.out.println("-------started------");
    }

}
