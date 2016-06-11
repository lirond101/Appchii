package com.myRemax.service;

import com.myRemax.dao.UserDAO;
import com.myRemax.hibernate_model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liron_d on 22/05/2016.
 */
@Service
@Transactional
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void insertUser(UsersEntity user) {
        userDAO.insertUser(user);
    }
    @Transactional
    public UsersEntity getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    @Transactional
    public UsersEntity getUser(String username) {
        return userDAO.getUser(username);
    }
    @Transactional
    public List<UsersEntity> getUsers() {
        return userDAO.getUsers();
    }
}
