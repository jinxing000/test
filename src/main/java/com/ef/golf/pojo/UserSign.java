package com.ef.golf.pojo;

import java.util.Date;

public class UserSign {
    private Integer id;//签到表id
    private Integer userId;//签到者id
    private Integer signcount;//连续签到次数
    private Integer count;//签到次数
    private Date lastModifyTime;//最后修改时间
    private String signHistoy;//签到历史时间 以逗号隔开

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSigncount() {
        return signcount;
    }

    public void setSigncount(Integer signcount) {
        this.signcount = signcount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getSignHistoy() {
        return signHistoy;
    }

    public void setSignHistoy(String signHistoy) {
        this.signHistoy = signHistoy;
    }

    public UserSign() {
    }

    public UserSign(Integer id, Integer userId, Integer signcount, Integer count, Date lastModifyTime, String signHistoy) {
        this.id = id;
        this.userId = userId;
        this.signcount = signcount;
        this.count = count;
        this.lastModifyTime = lastModifyTime;
        this.signHistoy = signHistoy;
    }
}