package com.cg.onlineshoppingms.userms.util;

import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtil
{

    /**
     * utility method to convert the entity object, User, to it's details class, UserDetailsResponse.
     * @param user
     * @return
     */
    public UserDetailsResponse toUserDetails(User user)
    {
        UserDetailsResponse userDetails=new UserDetailsResponse(user.getUserId(), user.getUsername(),
                user.getPassword(), user.getRoles());
        return userDetails;
    }

}
