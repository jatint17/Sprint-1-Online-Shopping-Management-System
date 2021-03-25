package com.cg.onlineshoppingms.userms.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String msg)
    {
        super(msg);
    }
}
