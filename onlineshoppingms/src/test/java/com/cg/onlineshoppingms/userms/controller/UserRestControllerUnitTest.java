package com.cg.onlineshoppingms.userms.controller;

import com.cg.onlineshoppingms.userms.dto.CheckCredentialsRequest;
import com.cg.onlineshoppingms.userms.dto.CreateUserRequest;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.exceptions.AddUserException;
import com.cg.onlineshoppingms.userms.exceptions.UserNotFoundException;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerUnitTest
{

    @Mock
    IUserService userService;
    @Mock
    UserUtil userUtil;
    @Spy
    @InjectMocks
    UserController userController;

    /**
     * Scenario: credentials are correct
     * input: mocking IUserService#checkCredentials(username,password), returning true and verifying it is called
     * expectation: asserting UserController#checkCredentials(request) is true
     */
    @Test
    public void testCheckCredentials_1()
    {
        String username="user";
        String password="password";
        CheckCredentialsRequest request = new CheckCredentialsRequest(username,password);
        when(userService.checkCredentials(username,password)).thenReturn(true);
        boolean result = userController.checkCredentials(request);
        Assertions.assertTrue(result);
        verify(userService).checkCredentials(username,password);
    }

    /**
     * Scenario: credentials are incorrect
     * input: mocking IUserService#checkCredentials(username,password), returning false and verifying it is called
     * expectation: asserting UserController#checkCredentials(request) is false
     */
    @Test
    public void testCheckCredentials_2()
    {
        String username="user";
        String password="password";
        CheckCredentialsRequest request = new CheckCredentialsRequest(username,password);
        when(userService.checkCredentials(username,password)).thenReturn(false);
        boolean result = userController.checkCredentials(request);
        Assertions.assertFalse(result);
        verify(userService).checkCredentials(username,password);
    }


    /**
     * Scenario: user found
     * input: mocking IUserService#findUserByUsername(username), returning User and verifying it is called,
     * mocking userUtil#toUserDetails(user), returning UserDetailsResponse and verifying it is called
     * expectation: asserting UserDetailsResponse userDetails and result is same
     */
    @Test
    public void testFindByUsername_1()
    {
        String username="user";
        User user = mock(User.class);
        UserDetailsResponse userDetails = mock(UserDetailsResponse.class);
        when(userService.findUserByUsername(username)).thenReturn(user);
        when(userUtil.toUserDetails(user)).thenReturn(userDetails);
        UserDetailsResponse result = userController.findByUsername(username);
        Assertions.assertSame(userDetails,result);
        verify(userService).findUserByUsername(username);
        verify(userUtil).toUserDetails(user);
    }

    /**
     * Scenario: user not found
     * input: stubbing IUserService#findUserByUsername(username), and throwing UserNotFoundException and verifying it is called
     * expectation: asserting UserController#findUserByUsername(username) throws UserNotFoundException
     */
    @Test
    public void testFindByUsername_2()
    {
        String username="user";
        doThrow(UserNotFoundException.class).when(userService).findUserByUsername(username);
        Executable executable = ()-> userController.findByUsername(username);
        Assertions.assertThrows(UserNotFoundException.class,executable);
        verify(userService).findUserByUsername(username);
    }

    @Test
    public void testAdd_1()
    {
        String username = "arpit";
        String password = "password";
        String role = "role1";
        Set<String>roles = new HashSet<>();
        roles.add(role);
        CreateUserRequest request = new CreateUserRequest(username,password,role);
        doThrow(AddUserException.class).when(userService).addUser(username,password,roles);
        Executable executable = () -> userController.addUser(request);
        assertThrows(AddUserException.class,executable);
    }

}
