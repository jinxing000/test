package com.ef.golf.pojo;

import java.util.Date;

public class Qiutong_order {
    private Integer qiutongOrderId;

    private Integer qiutongId;

    private Integer qiuhuiId;

    private Integer orderId;

    private String playDate;

    private String playTime;

    private String name;

    private String playName;

    private String qiuhuiName;

    private String contactsPhone;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private String createUser;

    private String modifyUser;

    public Integer getQiutongOrderId() {
        return qiutongOrderId;
    }

    public void setQiutongOrderId(Integer qiutongOrderId) {
        this.qiutongOrderId = qiutongOrderId;
    }

    public Integer getQiutongId() {
        return qiutongId;
    }

    public void setQiutongId(Integer qiutongId) {
        this.qiutongId = qiutongId;
    }

    public Integer getQiuhuiId() {
        return qiuhuiId;
    }

    public void setQiuhuiId(Integer qiuhuiId) {
        this.qiuhuiId = qiuhuiId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate == null ? null : playDate.trim();
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime == null ? null : playTime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName == null ? null : playName.trim();
    }

    public String getQiuhuiName() {
        return qiuhuiName;
    }

    public void setQiuhuiName(String qiuhuiName) {
        this.qiuhuiName = qiuhuiName == null ? null : qiuhuiName.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }
}