package com.ef.golf.vo;


/**
 * create by xzw
 * 2018年1月20日14:40:39
 * 保存帮我实现愿望的人的信息
 */
public class QuanziRealizeHopeVo {

    private String nickName;//支持者昵称
    private String gender;//支持者性别
    private String userType;//支持者身份类型
    private String headPortraitUrl;//头像url
    private String region;//上次登陆城市
    private String userId;//用户id
    private String grade;//等级
    private String payMoney;//支持金额

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }
}
