package com.cg.onlineshoppingms.userms.controller;

import com.cg.onlineshoppingms.userms.dto.CheckCredentialsRequest;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.UserServiceImpl;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@Import({UserController.class, UserServiceImpl.class, UserUtil.class})
@DataJpaTest
@AutoConfigureTestDatabase
public class UserRestControllerIntegrationTest
{
    @Autowired
    UserController userController;
    @Autowired
    EntityManager entityManager;

    /**
     * Scenario: Credentials match the database
     */
    @Test
    public void testCheckCredentials_1() {
        String username = "user";
        String password = "password";
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        User user = new User(username, password, roles);
        entityManager.persist(user);
        CheckCredentialsRequest request = new CheckCredentialsRequest(username, password);
        boolean result = userController.checkCredentials(request);
        Assertions.assertTrue(result);
    }
}