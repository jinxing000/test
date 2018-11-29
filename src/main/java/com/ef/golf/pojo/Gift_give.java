package com.ef.golf.pojo;

import java.io.Serializable;
import java.util.Date;

public class Gift_give implements Serializable{
    private Integer giftRecordId;

    private Integer userId;

    private Integer dynamicId;

    private Integer giftId;

    private Integer recieverId;

    private String groupId;

    private Date createTime;

    public Gift_give() {
    }

    public Gift_give(Integer giftRecordId, Integer userId, Integer dynamicId, Integer giftId, Integer recieverId, String groupId, Date createTime) {
        this.giftRecordId = giftRecordId;
        this.userId = userId;
        this.dynamicId = dynamicId;
        this.giftId = giftId;
        this.recieverId = recieverId;
        this.groupId = groupId;
        this.createTime = createTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Integer recieverId) {
        this.recieverId = recieverId;
    }

    public Integer getGiftRecordId() {
        return giftRecordId;
    }

    public void setGiftRecordId(Integer giftRecordId) {
        this.giftRecordId = giftRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}