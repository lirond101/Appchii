package com.myRemax.hibernate_model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "roles", schema = "", catalog = "remax")
public class RolesEntity {
    private Integer idrole;
    private String rolename;
    private Collection<GlobalTasksEntity> globalTasksesByIdrole;
    private Collection<UsersEntity> usersesByIdrole;

    @Id
    @Column(name = "idrole", nullable = false, insertable = true, updatable = true)
    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "rolename", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (idrole != null ? !idrole.equals(that.idrole) : that.idrole != null) return false;
        if (rolename != null ? !rolename.equals(that.rolename) : that.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrole != null ? idrole.hashCode() : 0;
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rolesByRecipient")
    public Collection<GlobalTasksEntity> getGlobalTasksesByIdrole() {
        return globalTasksesByIdrole;
    }

    public void setGlobalTasksesByIdrole(Collection<GlobalTasksEntity> globalTasksesByIdrole) {
        this.globalTasksesByIdrole = globalTasksesByIdrole;
    }

    @OneToMany(mappedBy = "rolesByRole")
    public Collection<UsersEntity> getUsersesByIdrole() {
        return usersesByIdrole;
    }

    public void setUsersesByIdrole(Collection<UsersEntity> usersesByIdrole) {
        this.usersesByIdrole = usersesByIdrole;
    }
}
