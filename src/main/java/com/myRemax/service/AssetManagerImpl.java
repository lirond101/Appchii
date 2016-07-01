package com.myRemax.service;

import com.myRemax.dao.AssetDAO;
import com.myRemax.hibernate_model.AssetsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liron_d on 23/05/2016.
 */

@Service
@Transactional
public class AssetManagerImpl implements AssetManager {

    @Autowired
    private AssetDAO assetDAO;

    @Transactional
    public Integer insertAsset(AssetsEntity asset) {  return assetDAO.insertAsset(asset);  }

    @Transactional
    public AssetsEntity getAsset(int assetID) {  return assetDAO.getAsset(assetID);  }

    @Transactional
    public List<AssetsEntity> getAssetByFullAddress(String city, String street, String num_Address) {  return assetDAO.getAssetByFullAddress(city, street, num_Address);  }

    @Transactional
    public List<AssetsEntity> getAssetByParams(String city,
                                        String type,Integer agent,
                                        String fromFloor, String toFloor, String fromPrice,
                                        String toPrice, String neighborhood,
                                        String rooms, String mamad,
                                        String airCon,  String elevator,
                                        String square,   String status)
    {  return assetDAO.getAssetByParams(city, type, agent, fromFloor,toFloor, fromPrice, toPrice, neighborhood, rooms, mamad, airCon, elevator, square, status);   }

    @Transactional
    public List<AssetsEntity> getAssets() {   return assetDAO.getAssets();   }

    @Transactional
    public List<AssetsEntity> getAssetsByAgent(String agentUN) {  return assetDAO.getAssetsByAgent(agentUN); }

    public AssetsEntity getAssetByStreet(String street) {
        return null;
    }
}
