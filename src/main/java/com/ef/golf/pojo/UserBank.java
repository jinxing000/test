package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/5/31 10:35
 */
public class UserBank {

    private Long id;
    private Long userId;//用户id
    private String bankPhone;//银行卡预留手机号
    private String bankCard;//银行卡号
    private String iderName;//持卡人姓名
    private String idNumber;//证件号
    private Date createTime;//创建时间
    private Long createUser;//创建人
    private String bankName;

    public UserBank() {
    }

    public UserBank(Long id, Long userId, String bankPhone, String bankCard, String iderName, String idNumber, Date createTime, Long createUser, String bankName) {
        this.id = id;
        this.userId = userId;
        this.bankPhone = bankPhone;
        this.bankCard = bankCard;
        this.iderName = iderName;
        this.idNumber = idNumber;
        this.createTime = createTime;
        this.createUser = createUser;
        this.bankName = bankName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getIderName() {
        return iderName;
    }

    public void setIderName(String iderName) {
        this.iderName = iderName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
