package com.myRemax.dao;

import com.myRemax.hibernate_model.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liron_d on 22/05/2016.
 */

@Service
public class UserDaoImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void insertUser(UsersEntity user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public UsersEntity getUserById(int userId) {
        return (UsersEntity) sessionFactory.
                getCurrentSession().
                get(UsersEntity.class, userId);
    }

    public UsersEntity getUser(String username) {
        Query query = sessionFactory.
                getCurrentSession().
                createQuery("from UsersEntity where username = :username");
        query.setParameter("username", username);
        return (UsersEntity) query.list().get(0);
    }

    public Integer getUserByName(String name) {
        Query query = sessionFactory.
                getCurrentSession().
                createQuery("from UsersEntity where firstname like :name");
        query.setParameter("name", '%' + name + '%');
        UsersEntity usersEntity = (UsersEntity) query.list().get(0);
        return usersEntity.getUserid();
    }

    public List<UsersEntity> getUsers() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(UsersEntity.class);
        return criteria.list();
    }
}
