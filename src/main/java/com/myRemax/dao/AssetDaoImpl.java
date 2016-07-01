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

    public Integer insertAsset(AssetsEntity asset) {
        return (Integer)sessionFactory.getCurrentSession().save(asset);
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
                                        String type, Integer agent,
                                        String fromFloor, String toFloor, String fromPrice,
                                        String toPrice, String neighborhood,
                                        String rooms,String mamad,
                                        String airCon,  String elevator,
                                        String square,   String status) {

        boolean  isType = false, isAgent = false, isFromFloor = false, isToFloor = false, isFromPrice = false, isToPrice = false,
                isNeighborhood = false, isRooms = false, isMamad = false, isAirConditioner = false, isElevator = false, isSquare = false, isStatus = false;
        boolean isAndHasUsed = false;
        if (type != "")
            isType = true;
//        if (street != "")
//            isStreet = true;
        if (agent != null) {
            isAgent = true;
        }
        if (fromFloor != "")
            isFromFloor = true;
        if (toFloor != "")
            isToFloor = true;
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

        String queryToBuild = "from AssetsEntity where city=:city AND";

        if (isType) {
            if (!isAndHasUsed) {
                queryToBuild += " type=:type";
                isAndHasUsed = true;
            } else queryToBuild += " AND type=:type";
        }

        if (isAgent) {
            if (!isAndHasUsed) {
                queryToBuild += " usersByAgent.userid=:agent";
                isAndHasUsed = true;
            } else queryToBuild += " AND usersByAgent.userid=:agent";
        }

        if (isFromFloor && isToFloor) {
            if (!isAndHasUsed) {
                queryToBuild += " floor BETWEEN :fromFloor AND :toFloor";
                isAndHasUsed = true;
            } else queryToBuild += " AND floor BETWEEN :fromFloor AND :toFloor" ;
        }

        if (isFromPrice && isToPrice) {
            if (!isAndHasUsed) {
                queryToBuild += " price BETWEEN :fromPrice AND :toPrice";
                isAndHasUsed = true;
            } else queryToBuild += " AND price BETWEEN :fromPrice AND :toPrice";
        }

        if (isNeighborhood) {
            if (!isAndHasUsed) {
                queryToBuild += " neighborhood=:neighborhood";
                isAndHasUsed = true;
            } else queryToBuild += " AND neighborhood=:neighborhood";
        }

        if (isRooms) {
            if (!isAndHasUsed) {
                queryToBuild += " rooms=:rooms";
                isAndHasUsed = true;
            } else queryToBuild += " AND rooms=:rooms";
        }

        if (isMamad) {
            if (!isAndHasUsed) {
                queryToBuild += " mamad=:mamad";
                isAndHasUsed = true;
            } else queryToBuild += " AND mamad=:mamad";
        }

        if (isAirConditioner){
            if (!isAndHasUsed) {
                queryToBuild += " air_Conditioner=:airCon";
                isAndHasUsed = true;
            } else queryToBuild += " AND air_Conditioner=:airCon";
        }

        if (isElevator) {
            if (!isAndHasUsed) {
                queryToBuild += " elevator=:elevator";
                isAndHasUsed = true;
            } else queryToBuild += " AND elevator=:elevator";
        }

        if (isSquare) {
            if (!isAndHasUsed) {
                queryToBuild += " square=:square";
                isAndHasUsed = true;
            } else queryToBuild += " AND square=:square";
        }

        if (isStatus) {
            if (!isAndHasUsed) {
                queryToBuild += " status=:status";
            } else queryToBuild += " AND status=:status";
        }

        if (queryToBuild == "from AssetsEntity where city=:city AND")
            queryToBuild = queryToBuild.substring(0, 34);

        Query query = sessionFactory.
                getCurrentSession().
                createQuery(queryToBuild);
        query.setParameter("city", "באר שבע");
        if(isType)
            query.setParameter("type", type);
        if(isAgent)
            query.setParameter("agent", agent);
        if(isFromFloor)
            query.setParameter("fromFloor", Float.parseFloat(fromFloor));
        if(isToFloor)
            query.setParameter("toFloor", Float.parseFloat(toFloor));
        if(isFromPrice)
            query.setParameter("fromPrice", Integer.parseInt(fromPrice));
        if(isToPrice)
            query.setParameter("toPrice", Integer.parseInt(toPrice));
        if(isNeighborhood)
            query.setParameter("neighborhood", neighborhood);
        if(isRooms)
            query.setParameter("rooms", Float.parseFloat(rooms));
        if(isMamad)
            query.setParameter("mamad", Byte.parseByte("1"));
        if(isAirConditioner)
            query.setParameter("airCon", Byte.parseByte("1"));
        if(isElevator)
            query.setParameter("elevator", Byte.parseByte("1"));
        if(isSquare)
            query.setParameter("square", Float.parseFloat(square));
        if(isStatus)
            query.setParameter("status", status);
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
