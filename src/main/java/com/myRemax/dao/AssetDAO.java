package com.myRemax.dao;

import com.myRemax.hibernate_model.AssetsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liron_d on 23/05/2016.
 */

@Service
public interface AssetDAO {
    Integer insertAsset(AssetsEntity asset);

    AssetsEntity getAsset(int assetID);

    List<AssetsEntity> getAssetByFullAddress(String city, String street, String num_Address);

    List<AssetsEntity> getAssetByParams(String city,
                                        String type, Integer agent,
                                        String fromFloor, String toFloor, String fromPrice,
                                        String toPrice, String neighborhood,
                                        String rooms,String mamad,
                                        String airCon,  String elevator,
                                        String square,   String status);

    List<AssetsEntity> getAssets();

    List<AssetsEntity> getAssetsByAgent(String agentUN);

    AssetsEntity getAssetByStreet(String street);
}
