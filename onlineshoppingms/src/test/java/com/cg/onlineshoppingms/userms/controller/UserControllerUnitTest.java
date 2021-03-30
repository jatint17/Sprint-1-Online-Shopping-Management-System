package com.cg.onlineshoppingms.userms.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.onlineshoppingms.userms.dto.CreateUserRequest;
import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.exceptions.AddUserException;
import com.cg.onlineshoppingms.userms.exceptions.InvalidPasswordException;
import com.cg.onlineshoppingms.userms.exceptions.InvalidUsernameException;
import com.cg.onlineshoppingms.userms.service.UserServiceImpl;
import com.cg.onlineshoppingms.userms.util.UserUtil;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {
	@Mock
	UserServiceImpl service;
	@Mock
	UserUtil util;
	@Spy
	@InjectMocks
	UserController controller;
	/**
	 * Scenario: User added successfully
	 * input: object of CreateUserRequest class and stubbing the following methods-
     * 		UserServiceImpl# addUser(username, password, set of roles), UserUtil# toUserDetails(user)
     * expectation: verifying if all stubbed methods have been called and user is added successfully
	 */
	@Test
	public void testAddUser_1() {
		String username = "username";
		String password = "password";
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		User user = mock(User.class);
		when(service.addUser(username, password, roles)).thenReturn(user);
		UserDetailsResponse details = mock(UserDetailsResponse.class);
		when(util.toUserDetails(user)).thenReturn(details);
		UserDetailsResponse response = controller.addUser(request);
		assertEquals(details, response);
		verify(service).addUser(username, password, roles);
		verify(util).toUserDetails(user);
	}
	
	/**
	 * Scenario: User not added successfully due to username validation failure
	 * input: object of CreateUserRequest class and stubbing the following methods- 
	 * 			UserServiceImpl# addUser(blank username, password, roles)
	 * expectation: verifying if InvalidUsernameException is thrown
	 */
	@Test
	public void testAddUser_2() {
		String username = "";
		String password = "password";
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		doThrow(InvalidUsernameException.class).when(service).addUser(username, password, roles);
		Executable executable = ()-> controller.addUser(request);
		assertThrows(InvalidUsernameException.class, executable);
		verify(service).addUser(username, password, roles);
	}
	
	/**
	 * Scenario: user not added successfully because blank password provided
	 * input: object of CreateUserRequest class and stubbing the following methods- 
	 * 			UserServiceImpl# addUser(username, blank password, roles)
	 * expectation: verifying if InvalidPasswordException is thrown
	 */
	@Test
	public void testAddUser_3() {
		String username = "username";
		String password = "";
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		doThrow(InvalidPasswordException.class).when(service).addUser(username, password, roles);
		Executable executable = ()-> controller.addUser(request);
		assertThrows(InvalidPasswordException.class, executable);
		verify(service).addUser(username, password, roles);
	}
	
	/**
	 * Scenario: user not added successfully because null username provided
	 * input: object of CreateUserRequest class and stubbing the following methods- 
	 * 			UserServiceImpl# addUser(null username, password, roles)
	 * expectation: verifying if InvalidUsernameException is thrown
	 */
	@Test
	public void testAddUser_4() {
		String username = null;
		String password = "password";
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		doThrow(InvalidUsernameException.class).when(service).addUser(username, password, roles);
		Executable executable = ()-> controller.addUser(request);
		assertThrows(InvalidUsernameException.class, executable);
		verify(service).addUser(username, password, roles);
	}
	
	/**
	 * Scenario: user not added successfully because null password provided
	 * input: object of CreateUserRequest class and stubbing the following methods- 
	 * 			UserServiceImpl# addUser(username, null password, roles)
	 * expectation: verifying if InvalidPasswordException is thrown
	 */
	@Test
	public void testAddUser_5() {
		String username = "username";
		String password = null;
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		doThrow(InvalidPasswordException.class).when(service).addUser(username, password, roles);
		Executable executable = ()-> controller.addUser(request);
		assertThrows(InvalidPasswordException.class, executable);
		verify(service).addUser(username, password, roles);
	}
	
	/**
	 * Scenario: user not added successfully because username already exists
	 * input: object of CreateUserRequest class and stubbing the following methods- 
	 * 			UserServiceImpl# addUser(username, password, roles)
	 * expectation: verifying if AddUserException is thrown
	 */
	@Test
	public void testAddUser_6() {
		String username = "username";
		String password = "password";
		String role = "admin";
		Set<String> roles = new HashSet<>();
		roles.add(role);
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setRole(role);
		doThrow(AddUserException.class).when(service).addUser(username, password, roles);
		Executable executable = ()-> controller.addUser(request);
		assertThrows(AddUserException.class, executable);
		verify(service).addUser(username, password, roles);
	}
}
