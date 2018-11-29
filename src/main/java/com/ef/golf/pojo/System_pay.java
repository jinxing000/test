package com.ef.golf.pojo;

import java.util.Date;

public class System_pay {
    private Integer sysPayId;

    private Integer userId;

    private Integer takeId;

    private Double money;

    private Date createTime;

    private Date modifyTime;

    private String type;

    private String state;//状态（1成功 2失败）

    private String orderNo;

    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getSysPayId() {
        return sysPayId;
    }

    public void setSysPayId(Integer sysPayId) {
        this.sysPayId = sysPayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTakeId() {
        return takeId;
    }

    public void setTakeId(Integer takeId) {
        this.takeId = takeId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}