package com.myRemax.hibernate_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "users", schema = "", catalog = "ebdb")
public class UsersEntity {
    private Integer userid;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String telNum;
    private String email;
    private Date dayOfCreate;
    private Collection<AssetsEntity> assetsesByUserid;
    private Collection<FavoritesEntity> favoritesByUserid;
    private Collection<GlobalTasksEntity> globalTasksesByUserid;
    private RolesEntity rolesByRole;

    @Id
    @Column(name = "userid", nullable = false, insertable = true, updatable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname", nullable = true, insertable = true, updatable = true, length = 45)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, insertable = true, updatable = true, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "tel_num", nullable = true, insertable = true, updatable = true, length = 45)
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @Basic
//    @Column(name = "role", nullable = true, insertable = true, updatable = true)
//    public Integer getRole() {
//        return role;
//    }
//
//    public void setRole(Integer role) {
//        this.role = role;
//    }

    @Basic
    @Column(name = "day_of_create", nullable = true, insertable = true, updatable = true)
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

        UsersEntity that = (UsersEntity) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (telNum != null ? !telNum.equals(that.telNum) : that.telNum != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
//        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (dayOfCreate != null ? !dayOfCreate.equals(that.dayOfCreate) : that.dayOfCreate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (telNum != null ? telNum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (dayOfCreate != null ? dayOfCreate.hashCode() : 0);
        return result;
    }

//    @JsonManagedReference
    @OneToMany(mappedBy = "usersByAgent")
    public Collection<AssetsEntity> getAssetsesByUserid() {
        return assetsesByUserid;
    }

    public void setAssetsesByUserid(Collection<AssetsEntity> assetsesByUserid) {
        this.assetsesByUserid = assetsesByUserid;
    }

    @OneToMany(mappedBy = "usersByUser")
    public Collection<FavoritesEntity> getFavoritesByUserid() {
        return favoritesByUserid;
    }

    public void setFavoritesByUserid(Collection<FavoritesEntity> favoritesByUserid) {
        this.favoritesByUserid = favoritesByUserid;
    }

    @OneToMany(mappedBy = "usersByAuthor")
    public Collection<GlobalTasksEntity> getGlobalTasksesByUserid() {
        return globalTasksesByUserid;
    }

    public void setGlobalTasksesByUserid(Collection<GlobalTasksEntity> globalTasksesByUserid) {
        this.globalTasksesByUserid = globalTasksesByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "idrole")
    public RolesEntity getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(RolesEntity rolesByRole) {
        this.rolesByRole = rolesByRole;
    }
}
