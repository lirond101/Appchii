package com.myRemax.service;

import com.myRemax.hibernate_model.AssetsEntity;

import java.util.List;

/**
 * Created by liron_d on 23/05/2016.
 */
public interface AssetManager {
    void insertAsset(AssetsEntity asset);

    AssetsEntity getAsset(int assetID);

    List<AssetsEntity> getAssetByFullAddress(String city, String street, String num_Address);

    List<AssetsEntity> getAssets();

    List<AssetsEntity> getAssetsByAgent(int agentID);

    AssetsEntity getAssetByStreet(String street);
}