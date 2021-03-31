package com.cg.onlineshoppingms;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.service.UserServiceImpl;

@SpringBootApplication
public class ShoppingApplication
{
    public static void main(String[] args)
    {
       ConfigurableApplicationContext context = SpringApplication.run(ShoppingApplication.class, args);
       IUserService service = context.getBean(IUserService.class);
       Set<String> roles = new HashSet<>();
       roles.add("admin");
       User admin = service.addUser("admin", "admin", roles);
    }
}
