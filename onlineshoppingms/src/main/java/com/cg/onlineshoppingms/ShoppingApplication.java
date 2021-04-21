package com.cg.onlineshoppingms;

import java.util.HashSet;
import java.util.Set;
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
       IUserService service = context.getBean(IUserService.class);
       Set<String> roles = new HashSet<>();
       roles.add("admin");
       User admin = service.addUser("admin", "admin", roles);
    }
    
    /**
    *
    * for handling cross origin requests
    */
   @Bean
   public CorsFilter corsFilter(){
       UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
       CorsConfiguration configuration=new CorsConfiguration();
       configuration.setAllowCredentials(true);
       configuration.addAllowedHeader("*");
       configuration.addAllowedOrigin("http://localhost:3000");
       configuration.addAllowedMethod("*");
       src.registerCorsConfiguration("/**",configuration);
       return new CorsFilter(src);
   }
}
