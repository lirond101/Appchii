package com.myRemax.hibernate_model;

import javax.persistence.*;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "customers", schema = "", catalog = "ebdb")
public class CustomersEntity {
    private Integer customersid;
    private String firstname;
    private String lastname;
    private String telNum;

    @Id
    @Column(name = "customersid", nullable = false, insertable = true, updatable = true)
    public Integer getCustomersid() {
        return customersid;
    }

    public void setCustomersid(Integer customersid) {
        this.customersid = customersid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomersEntity that = (CustomersEntity) o;

        if (customersid != null ? !customersid.equals(that.customersid) : that.customersid != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (telNum != null ? !telNum.equals(that.telNum) : that.telNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customersid != null ? customersid.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (telNum != null ? telNum.hashCode() : 0);
        return result;
    }
}
