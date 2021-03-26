package com.cg.onlineshoppingms.userms.service;

import com.cg.onlineshoppingms.userms.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface IUserService
{

    User addUser(String username, String password, Set<String> roles);
    User findById(Long userId);
    boolean checkCredentials(String username, String password);
    User findUserByUsername(String username);
}
