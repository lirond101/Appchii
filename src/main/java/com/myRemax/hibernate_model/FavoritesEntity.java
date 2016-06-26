package com.myRemax.hibernate_model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "favorites", schema = "", catalog = "ebdb")
@IdClass(FavoritesEntityPK.class)
public class FavoritesEntity {
    private Integer user;
    private Integer asset;
    private Date dayOfCreate;
    @org.codehaus.jackson.annotate.JsonManagedReference
    private UsersEntity usersByUser;
    @org.codehaus.jackson.annotate.JsonManagedReference
    private AssetsEntity assetsByAsset;

    @Id
    @Column(name = "user", nullable = false, insertable = false, updatable = false)
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Id
    @Column(name = "asset", nullable = false, insertable = false, updatable = false)
    public Integer getAsset() {
        return asset;
    }

    public void setAsset(Integer asset) {
        this.asset = asset;
    }

    @Id
    @Column(name = "day_of_create", nullable = false, insertable = true, updatable = true)
    public Date getDayOfCreate() {
        return dayOfCreate;
    }

    public void setDayOfCreate(Date dayOfCreate) {
        this.dayOfCreate = dayOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritesEntity that = (FavoritesEntity) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (asset != null ? !asset.equals(that.asset) : that.asset != null) return false;
        if (dayOfCreate != null ? !dayOfCreate.equals(that.dayOfCreate) : that.dayOfCreate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (asset != null ? asset.hashCode() : 0);
        result = 31 * result + (dayOfCreate != null ? dayOfCreate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUser() {
        return usersByUser;
    }

    public void setUsersByUser(UsersEntity usersByUser) {
        this.usersByUser = usersByUser;
    }

    @ManyToOne
    @JoinColumn(name = "asset", referencedColumnName = "assetid", nullable = false, insertable = false, updatable = false)
    public AssetsEntity getAssetsByAsset() {
        return assetsByAsset;
    }

    public void setAssetsByAsset(AssetsEntity assetsByAsset) {
        this.assetsByAsset = assetsByAsset;
    }
}
