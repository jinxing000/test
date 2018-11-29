package com.ef.golf.vo;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/4/24 17:26
 * 球会  门店
 */
public class ClubOrShopVo {

    private Integer userId;//球会或者门店id
    private String userType;//类型
    private String headPortraitUrl;//头像
    private String nickName;//名称
    private String settleItem;//可结算项或者门店地址

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSettleItem() {
        return settleItem;
    }

    public void setSettleItem(String settleItem) {
        this.settleItem = settleItem;
    }
}
