package com.myRemax.hibernate_model;

import javax.persistence.*;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "images", schema = "", catalog = "ebdb")
public class ImagesEntity {
    private Integer imagesid;
    private Integer asset;
    private String imagePath;
    private AssetsEntity assetsByAsset;

    @Id
    @Column(name = "imagesid", nullable = false, insertable = true, updatable = true)
    public Integer getImagesid() {
        return imagesid;
    }

    public void setImagesid(Integer imagesid) {
        this.imagesid = imagesid;
    }

//    @Basic
//    @Column(name = "asset", nullable = true, insertable = true, updatable = true)
//    public Integer getAsset() {
//        return asset;
//    }
//
//    public void setAsset(Integer asset) {
//        this.asset = asset;
//    }

    @Basic
    @Column(name = "image_path", nullable = true, insertable = true, updatable = true, length = 150)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagesEntity that = (ImagesEntity) o;

        if (imagesid != null ? !imagesid.equals(that.imagesid) : that.imagesid != null) return false;
        if (asset != null ? !asset.equals(that.asset) : that.asset != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imagesid != null ? imagesid.hashCode() : 0;
        result = 31 * result + (asset != null ? asset.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "asset", referencedColumnName = "assetid")
    public AssetsEntity getAssetsByAsset() {
        return assetsByAsset;
    }

    public void setAssetsByAsset(AssetsEntity assetsByAsset) {
        this.assetsByAsset = assetsByAsset;
    }
}
