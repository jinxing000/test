package com.ef.golf.pojo;

import java.util.Date;

public class Dynamic_praise {
    private Integer dynamicPraiseId;

    private Integer userId;

    private Integer dynamicId;

    private Date createTime;

    private String isAlive;

    public Integer getDynamicPraiseId() {
        return dynamicPraiseId;
    }

    public void setDynamicPraiseId(Integer dynamicPraiseId) {
        this.dynamicPraiseId = dynamicPraiseId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(String isAlive) {
        this.isAlive = isAlive == null ? null : isAlive.trim();
    }
}