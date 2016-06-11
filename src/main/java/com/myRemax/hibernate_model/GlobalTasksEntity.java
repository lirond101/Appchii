package com.myRemax.hibernate_model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by liron_d on 07/04/2016.
 */
@Entity
@Table(name = "global_tasks", schema = "", catalog = "ebdb")
public class GlobalTasksEntity {
    private Integer globalTasksid;
    private Date dayOfCreate;
    private Integer author;
    private String content;
    private Integer recipient;
    private UsersEntity usersByAuthor;
    private RolesEntity rolesByRecipient;

    @Id
    @Column(name = "global_tasksid", nullable = false, insertable = true, updatable = true)
    public Integer getGlobalTasksid() {
        return globalTasksid;
    }

    public void setGlobalTasksid(Integer globalTasksid) {
        this.globalTasksid = globalTasksid;
    }

    @Basic
    @Column(name = "day_of_create", nullable = true, insertable = true, updatable = true)
    public Date getDayOfCreate() {
        return dayOfCreate;
    }

    public void setDayOfCreate(Date dayOfCreate) {
        this.dayOfCreate = dayOfCreate;
    }

//    @Basic
//    @Column(name = "author", nullable = true, insertable = true, updatable = true)
//    public Integer getAuthor() {
//        return author;
//    }

//    public void setAuthor(Integer author) {
//        this.author = author;
//    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true, length = 300)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    @Basic
//    @Column(name = "recipient", nullable = true, insertable = true, updatable = true)
//    public Integer getRecipient() {
//        return recipient;
//    }
//
//    public void setRecipient(Integer recipient) {
//        this.recipient = recipient;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalTasksEntity that = (GlobalTasksEntity) o;

        if (globalTasksid != null ? !globalTasksid.equals(that.globalTasksid) : that.globalTasksid != null)
            return false;
        if (dayOfCreate != null ? !dayOfCreate.equals(that.dayOfCreate) : that.dayOfCreate != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = globalTasksid != null ? globalTasksid.hashCode() : 0;
        result = 31 * result + (dayOfCreate != null ? dayOfCreate.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "userid")
    public UsersEntity getUsersByAuthor() {
        return usersByAuthor;
    }

    public void setUsersByAuthor(UsersEntity usersByAuthor) {
        this.usersByAuthor = usersByAuthor;
    }

    @ManyToOne
    @JoinColumn(name = "recipient", referencedColumnName = "idrole")
    public RolesEntity getRolesByRecipient() {
        return rolesByRecipient;
    }

    public void setRolesByRecipient(RolesEntity rolesByRecipient) {
        this.rolesByRecipient = rolesByRecipient;
    }
}
