package com.cg.onlineshoppingms.adminms.service;

import com.cg.onlineshoppingms.adminms.entities.Admin;
import com.cg.onlineshoppingms.adminms.exceptions.AdminNotFoundException;
import com.cg.onlineshoppingms.adminms.repository.IAdminRepository;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.repository.IUserRepository;
import com.cg.onlineshoppingms.userms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRepository userRepository;


    @Override
    public Admin add(String username, String password)
    {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        User user = userService.addUser(username, password, roles);
        Admin admin=new Admin();
        admin.setUser(user);
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(Long id) {
        Optional<Admin> optional = adminRepository.findById(id);
         if(!optional.isPresent()){
             throw new AdminNotFoundException("admin not found for id="+id);
         }
         return optional.get();
    }

    @Override
    public Admin findByUsername(String username){
       User user= userRepository.findUserByUsername(username);
       Admin admin=adminRepository.findAdminByUser(user);
      return admin;
    }


}
