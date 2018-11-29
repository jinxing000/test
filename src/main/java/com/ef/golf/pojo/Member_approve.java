package com.ef.golf.pojo;

import java.util.Date;

public class Member_approve {
    private Integer id;

    private Integer userId;//用户id

    private String realname;//真是姓名

    private String phoneNumber;//手机号

    private String memberCard;//会员卡号

    private Integer clubId;//球会id

    private String approveStatus;//认证状态 0 未 1 成功 2认证中 3 拒绝

    private String approveImgUp;

    private String approveImgDown;//替换为身份证号

    private Date createTime;

    private Date modifyTime;

    private String reason;//原因

    private String overTime;//有效期

    private Date approveTime;//审批时间

    private String lifelong;//'0:终身1：不是终身',

    private String clubName;//球会名

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(String memberCard) {
        this.memberCard = memberCard == null ? null : memberCard.trim();
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public String getApproveImgUp() {
        return approveImgUp;
    }

    public void setApproveImgUp(String approveImgUp) {
        this.approveImgUp = approveImgUp == null ? null : approveImgUp.trim();
    }

    public String getApproveImgDown() {
        return approveImgDown;
    }

    public void setApproveImgDown(String approveImgDown) {
        this.approveImgDown = approveImgDown == null ? null : approveImgDown.trim();
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getLifelong() {
        return lifelong;
    }

    public void setLifelong(String lifelong) {
        this.lifelong = lifelong;
    }
}