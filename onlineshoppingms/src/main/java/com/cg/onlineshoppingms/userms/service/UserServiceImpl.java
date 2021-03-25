package com.cg.onlineshoppingms.userms.service;


import com.cg.onlineshoppingms.userms.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.onlineshoppingms.userms.entity.UserEntity;
import com.cg.onlineshoppingms.userms.repository.IUserRepository;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserEntity addUser(String username, String password) {
        validateUsername(username);
        validatePassword(password);
        UserEntity user = userRepository.findUserByUsername(username);
        if (user!=null) {
            throw new AddUserException("Username already exists");
        }
        UserEntity created = new UserEntity(username, password);
        created = userRepository.save(created);
        return created;
    }

    @Override
    public UserEntity findById(Long userId) {
        validateId(userId);
        Optional<UserEntity> optional = userRepository.findById(userId);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        UserEntity user = optional.get();
        return user;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        UserEntity user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        boolean result = user.getUsername().equals(username) && user.getPassword().equals(password);
        return result;
    }

    public void validateUsername(String username) {
        if (username == null || username.isEmpty() || username.trim().isEmpty()) {
            throw new InvalidUsernameException("Username cannot be null or empty");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty() || password.trim().isEmpty()) {
            throw new InvalidPasswordException("Password cannot be null or empty");
        }
    }

    public void validateId(Long userId) {
        if (userId < 0) {
            throw new InvalidIdException("Invalid id");
        }
    }
}