package com.cg.onlineshoppingms.adminms.service;

import com.cg.onlineshoppingms.adminms.entities.Admin;

import java.util.Set;

public interface IAdminService {

    Admin add(String username, String password);

    Admin findById(Long id);

    Admin findByUsername(String username);

}
