package com.ef.golf.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/16.
 */
public class PlatformWorktime {
    private Integer id;
    private String sTime;
    private String xTime;
    private Double beforeHours;
    private Date createTime;
    private Date modifyTime;
    private Integer createUser;
    private Integer modifyUser;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getxTime() {
        return xTime;
    }

    public void setxTime(String xTime) {
        this.xTime = xTime;
    }

    public Double getBeforeHours() {
        return beforeHours;
    }

    public void setBeforeHours(Double beforeHours) {
        this.beforeHours = beforeHours;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Integer modifyUser) {
        this.modifyUser = modifyUser;
    }
}
