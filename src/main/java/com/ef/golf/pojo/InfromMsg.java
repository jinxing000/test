package com.ef.golf.pojo;

import java.util.Date;

public class InfromMsg {
    private Long infromId;

    private Long userId;

    private String readUnread;

    private String infromType;

    private String msgType;

    private Long productId;

    private Date createTime;

    private Date modifyTime;

    private String createUser;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String reserve4;

    private String reserve5;

    public InfromMsg() {
    }

    public InfromMsg(Long infromId, Long userId, String readUnread, String infromType, String msgType, Long productId, Date createTime, Date modifyTime, String createUser, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5) {
        this.infromId = infromId;
        this.userId = userId;
        this.readUnread = readUnread;
        this.infromType = infromType;
        this.msgType = msgType;
        this.productId = productId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.createUser = createUser;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
    }

    public Long getInfromId() {
        return infromId;
    }

    public void setInfromId(Long infromId) {
        this.infromId = infromId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReadUnread() {
        return readUnread;
    }

    public void setReadUnread(String readUnread) {
        this.readUnread = readUnread == null ? null : readUnread.trim();
    }

    public String getInfromType() {
        return infromType;
    }

    public void setInfromType(String infromType) {
        this.infromType = infromType == null ? null : infromType.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4 == null ? null : reserve4.trim();
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5 == null ? null : reserve5.trim();
    }
}