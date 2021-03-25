package com.cg.onlineshoppingms.userms.repository;

import com.cg.onlineshoppingms.userms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long>
{
    User findUserByUsername(String username);
}