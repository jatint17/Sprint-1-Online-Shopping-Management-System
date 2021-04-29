package com.cg.onlineshoppingms.controller;

import com.cg.onlineshoppingms.adminms.dto.AdminDetails;
import com.cg.onlineshoppingms.adminms.entities.Admin;
import com.cg.onlineshoppingms.adminms.service.IAdminService;
import com.cg.onlineshoppingms.adminms.util.AdminUtil;
import com.cg.onlineshoppingms.userms.dto.AddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@RequestMapping("/a/admins")
@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private AdminUtil adminUtil;

    /**
     * saves a new admin in the database.
     * @param request
     * @return UserDetailsResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public AdminDetails addAdmin(@RequestBody @Valid AddRequest request)
    {
        Admin admin = adminService.add(request.getUsername(), request.getPassword());
        AdminDetails response = adminUtil.toDetails(admin);
        return response;
    }

    @GetMapping("/byusername/{username}")
    public AdminDetails findByUsername(@PathVariable @NotBlank String username){
        Admin admin= adminService.findByUsername(username);
        AdminDetails details=adminUtil.toDetails(admin);
        return details;
    }

    @GetMapping("/byid/{id}")
    public AdminDetails findById(@PathVariable @Min(0) Long id){
       Admin admin= adminService.findById(id);
       AdminDetails details=adminUtil.toDetails(admin);
       return details;
    }
}
