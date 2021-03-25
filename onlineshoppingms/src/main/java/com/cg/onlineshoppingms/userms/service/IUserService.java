package com.cg.onlineshoppingms.userms.service;

import com.cg.onlineshoppingms.userms.entity.UserEntity;

public interface IUserService
{
    UserEntity addUser(String username, String password);
    UserEntity findById(Long userId);
    boolean checkCredentials(String username, String password);
}
