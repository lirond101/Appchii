package com.myRemax.service;

import com.myRemax.hibernate_model.UsersEntity;

import java.util.List;

/**
 * Created by liron_d on 22/05/2016.
 */
public interface UserManager {
    void insertUser(UsersEntity user);

    UsersEntity getUserById(int userId);

    UsersEntity getUser(String username);

    List<UsersEntity> getUsers();
}
