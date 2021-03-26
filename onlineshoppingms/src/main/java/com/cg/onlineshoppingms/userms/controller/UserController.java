package com.cg.onlineshoppingms.userms.controller;


import com.cg.onlineshoppingms.userms.dto.CreateUserRequest;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController
{

    @Autowired
    private IUserService userService;

    @Autowired
    private UserUtil userUtil;

    @PostMapping("/p/users/add")
    public UserDetailsResponse addUser(@RequestBody CreateUserRequest request)
    {
        Set<String> roles = new HashSet<>();
        roles.add(request.getRole());
        User user = userService.addUser(request.getUsername(), request.getPassword(), roles);
        return userUtil.toUserDetails(user);
    }

    @GetMapping("/success")
    public String loginSuccess()
    {
        return "Login Successful!";
    }

    @GetMapping("/loginfail")
    public String loginFail()
    {
        return "Login failed!";
    }

    @GetMapping("/logoutsuccess")
    public String logout()
    {
        return "Logout Successful!";
    }

}
