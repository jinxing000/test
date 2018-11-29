package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/4/24 14:26
 */
public class HopeOrder {

    private Integer id;
    private Integer orderId;
    private Integer userId;
    private Integer hopeId;
    private Double payMoney;
    private Date createTime;
    private Date modifyTime;
    private String createUser;
    private String modifyUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHopeId() {
        return hopeId;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}
