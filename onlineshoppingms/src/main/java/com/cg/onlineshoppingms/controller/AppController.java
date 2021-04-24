package com.cg.onlineshoppingms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AppController {

    /**
     * displays a success message when login is successful.
     *
     * @return String
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/success")
    public String loginSuccess() {
        return "successfully logged in";
    }


    /**
     * displays a success message when logout is successful.
     *
     * @return String
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logoutsuccess")
    public String logout() {
        return "Logout Successful!";
    }

}
