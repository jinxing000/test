package com.ef.golf.pojo;

import java.util.Date;

public class Hope_record {
    private Integer hopeRecordId;

    private Integer userId;

    private Integer hopeId;

    private Double payMoney;

    private String payState;

    private Date createTime;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    public Integer getHopeRecordId() {
        return hopeRecordId;
    }

    public void setHopeRecordId(Integer hopeRecordId) {
        this.hopeRecordId = hopeRecordId;
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

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public Hope_record(Integer hopeRecordId, Integer userId, Integer hopeId, Double payMoney, String payState, Date createTime, String reserved1, String reserved2, String reserved3) {
        this.hopeRecordId = hopeRecordId;
        this.userId = userId;
        this.hopeId = hopeId;
        this.payMoney = payMoney;
        this.payState = payState;
        this.createTime = createTime;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
        this.reserved3 = reserved3;
    }
}