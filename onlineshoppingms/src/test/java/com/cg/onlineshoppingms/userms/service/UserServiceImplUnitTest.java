package com.cg.onlineshoppingms.userms.service;

import com.cg.onlineshoppingms.userms.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import com.cg.onlineshoppingms.userms.exceptions.*;
import com.cg.onlineshoppingms.userms.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {
	@Mock
	IUserRepository userRepository;
	@Spy
	@InjectMocks
	UserServiceImpl userService;

	/*
	 * scenario : successfully added user
	 */
    /*
     * Scenario: successfully added user
     */
    @Test
    public void testAddUser_1() {
        String username = "arpit";
        String password = "password";
        UserEntity user = mock(UserEntity.class);
        UserEntity saved = mock(UserEntity.class);
        doNothing().when(userService).validateUsername(username);
        doNothing().when(userService).validatePassword(password);
        when(userRepository.findUserByUsername(username)).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(saved);
        UserEntity result = userService.addUser(username, password);
        assertNotNull(result);
        assertEquals(saved, result);
        verify(userService).validatePassword(password);
        verify(userService).validateUsername(username);
        verify(userRepository).save(any(UserEntity.class));
        verify(userRepository).findUserByUsername(username);
    }

    /*
     * Scenario: empty username is entered
     */
    @Test
    public void testAddUser_2() {
        String username = "";
        String password = "password";
        doThrow(InvalidUsernameException.class).when(userService).validateUsername(username);
        Executable executable = () -> userService.addUser(username, password);
        assertThrows(InvalidUsernameException.class, executable);
        verify(userService).validateUsername(username);
    }

    /*
     * Scenario: empty password is entered
     */
    @Test
    public void testAddUser_3() {
        String username = "arpit";
        String password = "";
        doThrow(InvalidPasswordException.class).when(userService).validatePassword(password);
        Executable executable = () -> userService.addUser(username, password);
        assertThrows(InvalidPasswordException.class, executable);
        verify(userService).validatePassword(password);
    }

    /*
     * Scenario: password is null
     */
    @Test
    public void testAddUser_4() {
        String username = "arpit";
        String password = null;
        doThrow(InvalidPasswordException.class).when(userService).validatePassword(password);
        Executable executable = () -> userService.addUser(username, password);
        assertThrows(InvalidPasswordException.class, executable);
        verify(userService).validatePassword(password);
    }

    /*
     * Scenario: username is null
     */
    @Test
    public void testAddUser_5() {
        String username = null;
        String password = "password";
        doThrow(InvalidUsernameException.class).when(userService).validateUsername(username);
        Executable executable = () -> userService.addUser(username, password);
        assertThrows(InvalidUsernameException.class, executable);
        verify(userService).validateUsername(username);
    }

    /*
     * Scenario: username already exists
     */
    @Test
    public void testAddUser_6() {
        String username = "arpit";
        String password = "password";
        UserEntity user = mock(UserEntity.class);
        doNothing().when(userService).validateUsername(username);
        doNothing().when(userService).validatePassword(password);
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        Executable executable = () -> userService.addUser(username, password);
        assertThrows(AddUserException.class,executable);
        verify(userService).validatePassword(password);
        verify(userService).validateUsername(username);
        verify(userRepository).findUserByUsername(username);
    }

    /*
     * Scenario: empty username as input
     */
    @Test
    public void testValidateUserName_1() {
        String username = "";
        Executable executable = () -> userService.validateUsername(username);
        assertThrows(InvalidUsernameException.class, executable);
    }

    /*
     * Scenario: null username as input
     */
    @Test
    public void testValidateUserName_2() {
        String username = null;
        Executable executable = () -> userService.validateUsername(username);
        assertThrows(InvalidUsernameException.class, executable);
    }

    /*
     * Scenario: valid username as input
     */
    @Test
    public void testValidateUserName_3() {
        String username = "arpit";
        userService.validateUsername(username);
    }

    /*
     * Scenario: empty password as input
     */
    @Test
    public void testValidatePassword_1() {
        String password = "";
        Executable executable = () -> userService.validatePassword(password);
        assertThrows(InvalidPasswordException.class, executable);
    }

    /*
     * Scenario: null password as input
     */
    @Test
    public void testValidatePassword_2() {
        String password = null;
        Executable executable = () -> userService.validatePassword(password);
        assertThrows(InvalidPasswordException.class, executable);
    }

    /*
     * Scenario: valid password as input
     */
    @Test
    public void testValidatePassword_3() {
        String password = "password";
        userService.validatePassword(password);
    }

    /**
     * Scenario: username is empty
     */
    @Test
    public void testCheckCredentials_1()
    {
        String username="";
        String password="password";
        boolean result = userService.checkCredentials(username,password);
        Assertions.assertFalse(result);
    }

    /**
     * Scenario: password is empty
     */
    @Test
    public void testCheckCredentials_2()
    {
        String username="username";
        String password="";
        boolean result = userService.checkCredentials(username,password);
        Assertions.assertFalse(result);
    }

    /**
     * Scenario: username does not match the database
     */
    @Test
    public void testCheckCredentials_3()
    {
        String username = "username";
        String password = "password";
        String enteredUsername = "wrong";
        UserEntity user = new UserEntity(username,password);
        Mockito.when(userRepository.findUserByUsername(enteredUsername)).thenReturn(null);
        boolean result = userService.checkCredentials(enteredUsername,password);
        Assertions.assertFalse(result);
        Mockito.verify(userRepository).findUserByUsername(enteredUsername);
    }

    /**
     * Scenario: password does not match the database
     */
    @Test
    public void testCheckCredentials_4()
    {
        String username = "username";
        String password = "password";
        String enteredPassword = "wrong";
        UserEntity user = new UserEntity(username,password);
        Mockito.when(userRepository.findUserByUsername(username)).thenReturn(user);
        boolean result = userService.checkCredentials(username,enteredPassword);
        Assertions.assertFalse(result);
        Mockito.verify(userRepository).findUserByUsername(username);
    }

    /**
     * Scenario: credentials are matching
     */
    @Test
    public void testCheckCredentials_5()
    {
        String username = "username";
        String password = "password";
        UserEntity user = new UserEntity(username,password);
        Mockito.when(userRepository.findUserByUsername(username)).thenReturn(user);
        boolean result = userService.checkCredentials(username,password);
        Assertions.assertTrue(result);
        Mockito.verify(userRepository).findUserByUsername(username);
    }

	/**
	 * Scenario: userid is negative
	 */
	@Test
	public void testFindById_1()
	{
		long userid=-1;
		doThrow(InvalidIdException.class).when(userService).validateId(userid);
		Executable executable=()->userService.findById(userid);
		Assertions.assertThrows(InvalidIdException.class, executable);
	}


	/**
	 * Scenario: userid does not exist in the database
	 */

	@Test
	public void testFindById_2()
	{
		long userid=100;
		doNothing().when(userService).validateId(userid);
		UserEntity user=mock(UserEntity.class);
		Optional<UserEntity>optional=Optional.empty();

		when(userRepository.findById(userid)).thenReturn(optional);
		Executable executable=()->userService.findById(userid);
		Assertions.assertThrows(UserNotFoundException.class,executable);
	}
	/**
	 * Scenario: userid exist in the database
	 */
	@Test
	public void testFindById_3()
	{
		long userid=3;
		doNothing().when(userService).validateId(userid);
		UserEntity user=mock(UserEntity.class);
		Optional<UserEntity>optional = Optional.of(user);
		when (userRepository.findById(userid)).thenReturn(optional);
		UserEntity result=userService.findById(userid);
		Assertions.assertEquals(user,result);
		verify (userRepository).findById(userid);
	}
}
