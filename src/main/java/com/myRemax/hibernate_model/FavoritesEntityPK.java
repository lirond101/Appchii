package com.myRemax.hibernate_model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by liron_d on 07/04/2016.
 */
public class FavoritesEntityPK implements Serializable {
    private Integer user;
    private Integer asset;
    private Date dayOfCreate;

    @Column(name = "user", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Column(name = "asset", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getAsset() {
        return asset;
    }

    public void setAsset(Integer asset) {
        this.asset = asset;
    }

    @Column(name = "day_of_create", nullable = false, insertable = true, updatable = true)
    @Id
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

        FavoritesEntityPK that = (FavoritesEntityPK) o;

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
}
