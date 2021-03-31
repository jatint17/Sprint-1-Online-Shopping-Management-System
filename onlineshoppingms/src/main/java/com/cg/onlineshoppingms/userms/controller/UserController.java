package com.cg.onlineshoppingms.userms.controller;


import com.cg.onlineshoppingms.userms.dto.CheckCredentialsRequest;
import com.cg.onlineshoppingms.userms.dto.AddRequest;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * saves a new admin in the database.
     * @param request
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/a/admin/add")
    public UserDetailsResponse addAdmin(@RequestBody AddRequest request)
    {
    	Set<String> roles = new HashSet<>();
    	roles.add("admin");
    	User user = userService.addUser(request.getUsername(), request.getPassword(), roles);
    	UserDetailsResponse response = userUtil.toUserDetails(user);
    	return response;
    }
    
    /**
     * finds user from database by userId and returns it's details.
     * @param userId
     * @return UserDetailsResponse
     */
    @GetMapping("/c/users/byid/{id}")
    public UserDetailsResponse findById(@PathVariable("id") Long userId)
    {
        User user = userService.findById(userId);
        return userUtil.toUserDetails(user);
    }

    /**
     * finds user from database by username and returns it's details.
     * @param username
     * @return UserDetailsResponse
     */
    @GetMapping("/p/users/byusername/{username}")
    public UserDetailsResponse findByUsername(@PathVariable("username") String username)
    {
        User user = userService.findUserByUsername(username);
        return userUtil.toUserDetails(user);
    }

    /**
     * returns true if the credentials match the database, else false
     * @param request
     * @return UserDetailsResponse
     */
    @GetMapping("/p/users/checkcredentials")
    public boolean checkCredentials(@RequestBody CheckCredentialsRequest request)
    {
        return userService.checkCredentials(request.getUsername(),request.getPassword());
    }

    /**
     * displays a success message when login is successful.
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/success")
    public String loginSuccess()
    {
        return "Login Successful!";
    }

    /**
     * displays a failure message when login fails.
     * @return UserDetailsResponse
     */
    @GetMapping("/loginfail")
    public String loginFail()
    {
        return "Login failed!";
    }

    /**
     * displays a success message when logout is successful.
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logoutsuccess")
    public String logout()
    {
        return "Logout Successful!";
    }

}
