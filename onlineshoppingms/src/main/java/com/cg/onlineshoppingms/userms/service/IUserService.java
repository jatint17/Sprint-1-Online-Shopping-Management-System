package com.cg.onlineshoppingms.userms.service;

import com.cg.onlineshoppingms.userms.entity.User;

public interface IUserService
{
    User addUser(String username, String password);
    User findById(Long userId);
    boolean checkCredentials(String username, String password);
}
