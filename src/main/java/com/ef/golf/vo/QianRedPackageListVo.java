package com.ef.golf.vo;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/5/9 11:14
 */
public class QianRedPackageListVo {

    private Integer receiverId;//抢红包者ID
    private String receiveTime;//抢到红包时间
    private Double smallMoneyAmount;//红包金额
    private String headPortraitUrl;//用户头像
    private String nickname;//用户昵称
    private String userType;//用户类型
    private String gender;//用户性别

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Double getSmallMoneyAmount() {
        return smallMoneyAmount;
    }

    public void setSmallMoneyAmount(Double smallMoneyAmount) {
        this.smallMoneyAmount = smallMoneyAmount;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
