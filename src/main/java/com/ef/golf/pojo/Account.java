package com.ef.golf.pojo;

import java.util.Date;

public class Account {
    private Integer accountId;

    private Long userId;

    private Date createTime;

    private Date modifyTime;

    private Double balance;/** 总余额 */
    private Double czBalance;/** 充值余额 */
    private Double srBalance;/** 收入余额 */
    private Double zsBalance;/** 赠送余额 */

    private String isAlive;//锁（1解锁 2锁定）

    public Account() {
    }

    public Account(Integer accountId, Long userId, Date createTime, Date modifyTime, Double balance, Double czBalance, Double srBalance, Double zsBalance, String isAlive) {
        this.accountId = accountId;
        this.userId = userId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.balance = balance;
        this.czBalance = czBalance;
        this.srBalance = srBalance;
        this.zsBalance = zsBalance;
        this.isAlive = isAlive;
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

    public Double getCzBalance() {
        return czBalance;
    }

    public void setCzBalance(Double czBalance) {
        this.czBalance = czBalance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(String isAlive) {
        this.isAlive = isAlive == null ? null : isAlive.trim();
    }
}