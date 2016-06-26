package com.myRemax.hibernate_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "assets", schema = "", catalog = "ebdb")
public class AssetsEntity {
    private Integer assetid;
    private String city;
    private String street;
    private String num_Address;
    private String type;
    private Date dayOfCreate;
    private Float floor;
    private Float numOfFloors;
    private Integer price;
    private String neighborhood;
    private Float rooms;
    private Byte mamad;
    private Byte airConditioner;
    private Byte elevator;
    private Float square;
    private String status;
    private String details;
    private String cust_Name;
    private String cust_Tel;

//    @JsonIgnore
    @org.codehaus.jackson.annotate.JsonManagedReference
    private UsersEntity usersByAgent;
    @JsonIgnore
    private Collection<FavoritesEntity> favoritesByAssetid;
    @JsonIgnore
    private Collection<ImagesEntity> imagesByAssetid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assetid", nullable = false, insertable = true, updatable = true)
    public Integer getAssetid() {
        return assetid;
    }

    public void setAssetid(Integer assetid) {
        this.assetid = assetid;
    }

    @Basic
    @Column(name = "city", nullable = false, insertable = true, updatable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, insertable = true, updatable = true, length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "num_Address", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNum_Address() {
        return num_Address;
    }

    public void setNum_Address(String num_Address) {
        this.num_Address = num_Address;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Basic
    @Column(name = "day_of_create", nullable = true, insertable = true, updatable = true)
    public Date getDayOfCreate() {
        return dayOfCreate;
    }

    public void setDayOfCreate(Date dayOfCreate) {
        this.dayOfCreate = dayOfCreate;
    }

    @Basic
    @Column(name = "numOfFloors", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(Float numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    @Basic
    @Column(name = "floor", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getFloor() {
        return floor;
    }

    public void setFloor(Float floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "price", nullable = true, insertable = true, updatable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "neighborhood", nullable = true, insertable = true, updatable = true, length = 45)
    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Basic
    @Column(name = "rooms", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getRooms() {
        return rooms;
    }

    public void setRooms(Float rooms) {
        this.rooms = rooms;
    }

    @Basic
    @Column(name = "mamad", nullable = true, insertable = true, updatable = true)
    public Byte getMamad() {
        return mamad;
    }

    public void setMamad(Byte mamad) {
        this.mamad = mamad;
    }

    @Basic
    @Column(name = "air_conditioner", nullable = true, insertable = true, updatable = true)
    public Byte getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Byte airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Basic
    @Column(name = "elevator", nullable = true, insertable = true, updatable = true)
    public Byte getElevator() {
        return elevator;
    }

    public void setElevator(Byte elevator) {
        this.elevator = elevator;
    }

    @Basic
    @Column(name = "square", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true, length = 100)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "details", nullable = true, insertable = true, updatable = true, length = 250)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "cust_Tel", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCust_Tel() {
        return cust_Tel;
    }

    public void setCust_Tel(String cust_Tel) {
        this.cust_Tel = cust_Tel;
    }

    @Basic
    @Column(name = "cust_Name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCust_Name() {
        return cust_Name;
    }

    public void setCust_Name(String cust_Name) {
        this.cust_Name = cust_Name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetsEntity that = (AssetsEntity) o;

        if (assetid != null ? !assetid.equals(that.assetid) : that.assetid != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (num_Address != null ? !num_Address.equals(that.num_Address) : that.num_Address != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (dayOfCreate != null ? !dayOfCreate.equals(that.dayOfCreate) : that.dayOfCreate != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (neighborhood != null ? !neighborhood.equals(that.neighborhood) : that.neighborhood != null) return false;
        if (rooms != null ? !rooms.equals(that.rooms) : that.rooms != null) return false;
        if (mamad != null ? !mamad.equals(that.mamad) : that.mamad != null) return false;
        if (airConditioner != null ? !airConditioner.equals(that.airConditioner) : that.airConditioner != null)
            return false;
        if (elevator != null ? !elevator.equals(that.elevator) : that.elevator != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (square != null ? !square.equals(that.square) : that.square != null) return false;
        if (cust_Name != null ? !cust_Name.equals(that.cust_Name) : that.cust_Name != null) return false;
        if (cust_Tel != null ? !cust_Tel.equals(that.cust_Tel) : that.cust_Tel != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assetid != null ? assetid.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (num_Address != null ? num_Address.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (dayOfCreate != null ? dayOfCreate.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (mamad != null ? mamad.hashCode() : 0);
        result = 31 * result + (airConditioner != null ? airConditioner.hashCode() : 0);
        result = 31 * result + (elevator != null ? elevator.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (square != null ? square.hashCode() : 0);
        result = 31 * result + (cust_Name != null ? cust_Name.hashCode() : 0);
        result = 31 * result + (cust_Tel != null ? cust_Tel.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
//    @JsonUnwrapped
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "agent", referencedColumnName = "userid")
    public UsersEntity getUsersByAgent() {
        return usersByAgent;
    }

    public void setUsersByAgent(UsersEntity usersByAgent) {
        this.usersByAgent = usersByAgent;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "assetsByAsset")
    public Collection<FavoritesEntity> getFavoritesByAssetid() {
        return favoritesByAssetid;
    }

    public void setFavoritesByAssetid(Collection<FavoritesEntity> favoritesByAssetid) {
        this.favoritesByAssetid = favoritesByAssetid;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "assetsByAsset")
    public Collection<ImagesEntity> getImagesByAssetid() {
        return imagesByAssetid;
    }

    public void setImagesByAssetid(Collection<ImagesEntity> imagesByAssetid) {
        this.imagesByAssetid = imagesByAssetid;
    }
}
