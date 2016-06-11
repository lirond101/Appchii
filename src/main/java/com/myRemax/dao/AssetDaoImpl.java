package com.myRemax.dao;

import com.myRemax.hibernate_model.AssetsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liron_d on 23/05/2016.
 */

@Service
public class AssetDaoImpl implements AssetDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void insertAsset(AssetsEntity asset) {
        sessionFactory.getCurrentSession().save(asset);
    }

    public AssetsEntity getAsset(int assetID) {
        return (AssetsEntity) sessionFactory.
                getCurrentSession().
                get(AssetsEntity.class, assetID);
    }

    public List<AssetsEntity> getAssetByFullAddress(String city, String street, String num_Address) {
        Query query = null;
        if (num_Address != "") {
        query = sessionFactory.
                getCurrentSession().
                createQuery("from AssetsEntity where city=:city and street=:street and num_Address=:num_Address");
        query.setParameter("city", city);
        query.setParameter("street", street);
        query.setParameter("num_Address", num_Address);
        }
        else {
            query = sessionFactory.
                    getCurrentSession().
                    createQuery("from AssetsEntity where city=:city and street=:street");
            query.setParameter("city", city);
            query.setParameter("street", street);
        }
        return query.list();
    }

    public List<AssetsEntity> getAssets() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(AssetsEntity.class);
        return criteria.list();
    }

    public List<AssetsEntity> getAssetsByAgent(int agentID){
        Query query = sessionFactory.
                getCurrentSession().
                createQuery("from AssetsEntity where usersByAgent.userid=:agentID");
        query.setParameter("agentID", agentID);
        return query.list();
    }

    public AssetsEntity getAssetByStreet(String street) {
        Query query = sessionFactory.
                getCurrentSession().
                createQuery("from AssetsEntity where street=:street");
        query.setParameter("street", street);
        return (AssetsEntity) query.list().get(0);
    }
}
