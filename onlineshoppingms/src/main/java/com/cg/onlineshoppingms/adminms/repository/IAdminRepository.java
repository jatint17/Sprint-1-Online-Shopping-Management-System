package com.cg.onlineshoppingms.adminms.repository;

import com.cg.onlineshoppingms.adminms.entities.Admin;
import com.cg.onlineshoppingms.userms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin,Long> {

    Admin findAdminByUser(User user);

}
