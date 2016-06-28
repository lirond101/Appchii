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

    public List<AssetsEntity> getAssetByParams(String city,
                                        String type,String agent,
                                        String floor, String fromPrice,
                                        String toPrice, String neighborhood,
                                        String rooms,String mamad,
                                        String airCon,  String elevator,
                                        String square,   String status) {

        boolean isStreet = false, isType = false, isAgent = false, isFloor = false, isFromPrice = false, isToPrice = false,
                isNeighborhood = false, isRooms = false, isMamad = false, isAirConditioner = false, isElevator = false, isSquare = false, isStatus = false;
        boolean isAndHasUsed = false;
        if (type != "")
            isType = true;
//        if (street != "")
//            isStreet = true;
        if (agent != "")
            isAgent = true;
        if (floor != "")
            isFloor = true;
        if (fromPrice != "")
            isFromPrice = true;
        if (toPrice != "")
            isToPrice = true;
        if (neighborhood != "")
            isNeighborhood = true;
        if (rooms != "")
            isRooms = true;
        if (mamad != "")
            isMamad = true;
        if (airCon != "")
            isAirConditioner = true;
        if (elevator != "")
            isElevator = true;
        if (status != "")
            isStatus = true;
        if (square != "")
            isSquare = true;

        String queryToBuild = "from AssetsEntity where city=:city";

//        if (isStreet) {
//            if (!isAndHasUsed) {
//                queryToBuild += " Street=" + street;
//                isAndHasUsed = true;
//            } else queryToBuild += " AND Street=" + street;
//        }

        if (isType) {
            if (isAndHasUsed) {
                queryToBuild += " Type=" + type;

            } else {queryToBuild += " AND Type=" + type;
            isAndHasUsed = true;}
        }

        if (isAgent) {
            if (isAndHasUsed) {
                queryToBuild += " Agent=" + agent;

            } else {queryToBuild += " AND Agent=" + agent;
                isAndHasUsed = true;}
        }

        if (isFloor) {
            if (!isAndHasUsed) {
                queryToBuild += " Floor=" + floor;
                isAndHasUsed = true;
            } else queryToBuild += " AND Floor=" + floor;
        }

        if (isFromPrice && isToPrice) {
            if (!isAndHasUsed) {
                queryToBuild += " Price BETWEEN " + fromPrice + " AND " + toPrice;
                isAndHasUsed = true;
            } else queryToBuild += " AND Price BETWEEN " + fromPrice + " AND " + toPrice;
        }

        if (isNeighborhood) {
            if (!isAndHasUsed) {
                queryToBuild += " Neighborhood=" + neighborhood;
                isAndHasUsed = true;
            } else queryToBuild += " AND Neighborhood=" + neighborhood;
        }

        if (isRooms) {
            if (!isAndHasUsed) {
                queryToBuild += " Rooms=" + rooms;
                isAndHasUsed = true;
            } else queryToBuild += " AND Rooms=" + rooms;
        }

        if (isMamad) {
            if (!isAndHasUsed) {
                queryToBuild += " Mamad=" + mamad;
                isAndHasUsed = true;
            } else queryToBuild += " AND Mamad=" + mamad;
        }

        if (isAirConditioner) {
            if (!isAndHasUsed) {
                queryToBuild += " Air_Conditioner=" + airCon;
                isAndHasUsed = true;
            } else queryToBuild += " AND Air_Conditioner=" + airCon;
        }

        if (isElevator) {
            if (!isAndHasUsed) {
                queryToBuild += " Elevator=" + elevator;
                isAndHasUsed = true;
            } else queryToBuild += " AND Elevator=" + elevator;
        }

        if (isSquare) {
            if (!isAndHasUsed) {
                queryToBuild += " Square=" + square;
                isAndHasUsed = true;
            } else queryToBuild += " AND Square=" + square;
        }

        if (isStatus) {
            if (!isAndHasUsed) {
                queryToBuild += " Status=" + status;
                isAndHasUsed = true;
            } else queryToBuild += " AND Status=" + status;
        }

        if (queryToBuild == "from AssetsEntity where city=:city")
            queryToBuild = queryToBuild.substring(0, 34);

        Query query = sessionFactory.
                getCurrentSession().
                createQuery(queryToBuild);
        query.setParameter("city", "באר שבע");
        return query.list();

    }

    public List<AssetsEntity> getAssets() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(AssetsEntity.class);
        return criteria.list();
    }

    public List<AssetsEntity> getAssetsByAgent(String agentUN){
        Query query = sessionFactory.
                getCurrentSession().
                createQuery("from AssetsEntity where usersByAgent.username=:agentUN");
        query.setParameter("agentUN", agentUN);
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
