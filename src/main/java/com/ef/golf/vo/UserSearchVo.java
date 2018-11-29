package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;

public class UserSearchVo extends Page{

    private Integer userId;//用户id

    private String nickname;//昵称

    private String userType;//用户类型

    private String region;//上次登陆地

    private String headPortraitUrl;//头像url

    private String gender;//性别

    private String phone;//联系方式

    private String lat;//经度

    private String lng;//纬度

    private String hasAttend="0";//是否关注，默认为0，未关注，1表示已关注

    private String exclusiveNo;

    public UserSearchVo() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHasAttend() {
        return hasAttend;
    }

    public void setHasAttend(String hasAttend) {
        this.hasAttend = hasAttend;
    }

    public String getExclusiveNo() {
        return exclusiveNo;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo;
    }

    public UserSearchVo(Integer userId, String nickname, String userType, String region, String headPortraitUrl, String phone, String lat, String lng) {
        this.userId = userId;
        this.nickname = nickname;
        this.userType = userType;
        this.region = region;
        this.headPortraitUrl = headPortraitUrl;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
