package com.cg.onlineshoppingms.adminms.util;

import com.cg.onlineshoppingms.adminms.dto.AdminDetails;
import com.cg.onlineshoppingms.adminms.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminUtil {

    public AdminDetails toDetails(Admin admin){
        AdminDetails details=new AdminDetails();
        details.setAdminId(admin.getAdminId());
        details.setUsername(admin.getUser().getUsername());
        return details;
    }

}
