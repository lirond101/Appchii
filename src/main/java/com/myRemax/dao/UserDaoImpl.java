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
        Query query = null;
        int index = name.indexOf(' ');
        if (index != -1 && name.length()-(index+1)>0) {
            String fn = name.substring(0, index);
            System.out.println(fn);
            String ln = name.substring(index+1, name.length());
            System.out.println(ln);
            query = sessionFactory.
                    getCurrentSession().
                    createQuery("from UsersEntity where firstname like :fname and lastname like :lname");
            query.setParameter("fname", '%' + fn + '%');
            query.setParameter("lname", '%' + ln + '%');
        }
        else{
            query = sessionFactory.
                    getCurrentSession().
                    createQuery("from UsersEntity where firstname like :fname");
            query.setParameter("fname", '%' + name + '%');
        }

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
