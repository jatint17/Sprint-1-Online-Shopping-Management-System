package com.cg.onlineshoppingms.userms.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserEntity")
public class User
{
    @GeneratedValue
    @Id
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;

    public User(){}
    public User(String username, String password)
    {
        this.username=username;
        this.password=password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
