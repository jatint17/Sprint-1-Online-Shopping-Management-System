package com.cg.onlineshoppingms.userms.controller;


import com.cg.onlineshoppingms.userms.dto.CreateUserRequest;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Validated
@RestController
public class UserController
{

    @Autowired
    private IUserService userService;

    @Autowired
    private UserUtil userUtil;

    /**
     * saves a new unique user in the database.
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/p/users/add")
    public UserDetailsResponse addUser(@RequestBody CreateUserRequest request)
    {
        Set<String> roles = new HashSet<>();
        roles.add(request.getRole());
        User user = userService.addUser(request.getUsername(), request.getPassword(), roles);
        return userUtil.toUserDetails(user);
    }

    /**
     * finds user from database by userId and returns it's details.
     * @param userId
     * @return
     */
    @GetMapping("/c/users/byid/{id}")
    public UserDetailsResponse findById(@PathVariable("id") Long userId)
    {
        User user = userService.findById(userId);
        return userUtil.toUserDetails(user);
    }

    @ResponseStatus(HttpStatus.OK)
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logoutsuccess")
    public String logout()
    {
        return "Logout Successful!";
    }

}
