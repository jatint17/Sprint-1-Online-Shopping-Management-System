package com.cg.onlineshoppingms.userms.service;

import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.exceptions.AddUserException;
import com.cg.onlineshoppingms.userms.exceptions.InvalidPasswordException;
import com.cg.onlineshoppingms.userms.exceptions.InvalidUsernameException;
import com.cg.onlineshoppingms.userms.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(UserServiceImpl.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserServiceImpIntegrationTest
{

    @Autowired
    UserServiceImpl userService;
    @Autowired
    EntityManager entityManager;

    /*
     * user added successfully
     */
    @Test
    public void testAddUser_1() {
        String username = "arpit";
        String password = "password";
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        User result = userService.addUser(username, password,roles);
        assertNotNull(result);
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        assertEquals(1,users.size());
        User stored = users.get(0);
        assertEquals(stored.getUserId(), result.getUserId());
        assertEquals(username, result.getUsername());
        assertEquals(username, stored.getUsername());
        assertEquals(password, result.getPassword());
        assertEquals(password, stored.getPassword());
    }
    /*
     * username is blank
     */
    @Test
    public void testAddUser_2() {
        String username = "";
        String password = "password";
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        Executable executable=()-> userService.addUser(username, password, roles);
        assertThrows(InvalidUsernameException.class, executable);
    }

    /*
     * password is blank
     */
    @Test
    public void testAddUser_3() {
        String username = "arpit";
        String password = "";
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        Executable executable=()-> userService.addUser(username, password, roles);
        assertThrows(InvalidPasswordException.class, executable);
    }

    /*
     * username is null
     */
    @Test
    public void testAddUser_4() {
        String username = null;
        String password = "password";
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        Executable executable=()-> userService.addUser(username, password, roles);
        assertThrows(InvalidUsernameException.class, executable);
    }

    /*
     * password is blank
     */
    @Test
    public void testAddUser_5() {
        String username = "arpit";
        String password = null;
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        Executable executable=()-> userService.addUser(username, password, roles);
        assertThrows(InvalidPasswordException.class, executable);
    }

    /*
     * username already exists
     */
    @Test
    public void testAddUser_6() {
        String username = "arpit";
        String password = "password";
        String role = "role1";
        Set<String> roles = new HashSet<>();
        roles.add(role);
        User user = userService.addUser(username,password,roles);
        Executable executable=()-> userService.addUser(username,password,roles);
        assertThrows(AddUserException.class, executable);
    }

    /**
     * Scenario: user exists
     */
    @Test
    public void testFindUserByUsername_1()
    {
        String username = "user";
        String password = "password";
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        User saved = new User(username,password,roles);
        entityManager.persist(saved);
        User result = userService.findUserByUsername(username);
        assertNotNull(result);
        assertEquals(saved,result);
    }

    /**
     * Scenario: user does not exist
     */
    @Test
    public void testFindUserByUsername_2()
    {
        String username = "user";
        String password = "password";
        String enteredUsername = "wrong";
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        User user = new User(username, password, roles);
        entityManager.persist(user);
        Executable executable = ()-> userService.findUserByUsername(enteredUsername);
        assertThrows(UserNotFoundException.class,executable);
    }
}