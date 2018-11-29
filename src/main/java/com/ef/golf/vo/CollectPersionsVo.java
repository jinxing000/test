package com.ef.golf.vo;



import com.ef.golf.core.structure.pageModel.Page;

import java.util.Date;

/**
 * create by xzw
 * 2017年12月25日14:28:42
 * 保存我关注人的  vo
 */
public class CollectPersionsVo extends Page{

    private Integer userId;//用户id
    private String createTime;//关注时间
    private String nickName;//昵称
    private String phone;
    private String headPortraitUrl;//被关注人的头像url 18002161386 Zy123456
    private String userType;//用户的类型
    private String gender;//性别
    private String grade;//练教等级
    private String vip;//vip等级
    private String remark;//备注
    private Integer distance;//距离
    private String region;
    private String lat;
    private String lng;

    private String hasAttend="1";//是否关注，默认为0，未关注，1表示已关注

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public CollectPersionsVo(){}

    public CollectPersionsVo(Integer userId, String createTime, String nickName, String phone, String headPortraitUrl, String userType, String gender, String grade, String vip, String remark, Integer distance, String region, String lat, String lng, String hasAttend) {
        this.userId = userId;
        this.createTime = createTime;
        this.nickName = nickName;
        this.phone = phone;
        this.headPortraitUrl = headPortraitUrl;
        this.userType = userType;
        this.gender = gender;
        this.grade = grade;
        this.vip = vip;
        this.remark = remark;
        this.distance = distance;
        this.region = region;
        this.lat = lat;
        this.lng = lng;
        this.hasAttend = hasAttend;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getHasAttend() {
        return hasAttend;
    }

    public void setHasAttend(String hasAttend) {
        this.hasAttend = hasAttend;
    }
}
