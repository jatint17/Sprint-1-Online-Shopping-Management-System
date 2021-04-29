package com.cg.onlineshoppingms.controller;

import com.cg.onlineshoppingms.userms.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedUserExceptionHandler
{

    public static final Logger log = LoggerFactory.getLogger(CentralizedUserExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e)
    {
    	log.error("inside handle user not found "+e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIdException.class)
    public String handleInvalidIdException(InvalidIdException e)
    {
    	log.error("inside handle invalid id "+e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUsernameException.class)
    public String handleInvalidUsernameException(InvalidUsernameException e)
    {
    	log.error("inside handle invalid username "+e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPasswordException.class)
    public String handleInvalidPasswordException(InvalidPasswordException e)
    {
    	log.error("inside handle invalid password "+e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AddUserException.class)
    public String handleAddUserException(AddUserException e)
    {
    	log.error("inside handle add user exception "+e);
        return e.getMessage();
    }

}
