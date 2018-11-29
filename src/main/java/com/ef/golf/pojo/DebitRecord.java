package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/4/28 16:59
 */
public class DebitRecord {

    private Integer debitRecordId;
    private Integer accountId;
    private Integer userId;
    private Double czBalance;
    private Double srBalance;
    private Double zsBalance;
    private Date createTime;
    private String payForm;
    private String pingId;
    private String channel;

    public String getPayForm() {
        return payForm;
    }

    public void setPayForm(String payForm) {
        this.payForm = payForm;
    }

    public String getPingId() {
        return pingId;
    }

    public void setPingId(String pingId) {
        this.pingId = pingId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDebitRecordId() {
        return debitRecordId;
    }

    public void setDebitRecordId(Integer debitRecordId) {
        this.debitRecordId = debitRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCzBalance() {
        return czBalance;
    }

    public void setCzBalance(Double czBalance) {
        this.czBalance = czBalance;
    }

    public Double getSrBalance() {
        return srBalance;
    }

    public void setSrBalance(Double srBalance) {
        this.srBalance = srBalance;
    }

    public Double getZsBalance() {
        return zsBalance;
    }

    public void setZsBalance(Double zsBalance) {
        this.zsBalance = zsBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
