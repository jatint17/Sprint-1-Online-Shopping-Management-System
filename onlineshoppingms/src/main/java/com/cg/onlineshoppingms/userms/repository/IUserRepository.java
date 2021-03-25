package com.cg.onlineshoppingms.userms.repository;

import com.cg.onlineshoppingms.userms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long>
{
    UserEntity findUserByUsername(String username);
}