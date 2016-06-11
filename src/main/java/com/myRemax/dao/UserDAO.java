package com.myRemax.dao;

import com.myRemax.hibernate_model.UsersEntity;

import java.util.List;

/**
 * Created by liron_d on 22/05/2016.
 */
public interface UserDAO {
    void insertUser(UsersEntity user);

    UsersEntity getUserById(int userId);

    UsersEntity getUser(String username);

    List<UsersEntity> getUsers();
}
