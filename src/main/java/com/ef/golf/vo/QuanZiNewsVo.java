package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;

import java.io.Serializable;
import java.util.Date;


/**
 * create by xzw
 * 2017年12月19日17:09:54
 * 查询圈子消息提示的  vo
 */
public class QuanZiNewsVo extends Page implements Serializable {

    private Integer userId;//评论或点赞的用户的id
    private String realName;//评论或者点赞人的名字
    private String userType;//用户类型
    private String headPortraitUrl;//头像url
    private String memberLevel;//会员等级
    private Date createTime;//评论或者点赞的时间
    private String isRead;//是否已读
    private Integer dynamicId;//动态id
    private Integer type;//1.评论提示 2.点赞提示


    public QuanZiNewsVo(){}

    public QuanZiNewsVo(Integer userId, String realName, String userType, String headPortraitUrl, String memberLevel, Date createTime, String isRead, Integer dynamicId, Integer type) {
        this.userId = userId;
        this.realName = realName;
        this.userType = userType;
        this.headPortraitUrl = headPortraitUrl;
        this.memberLevel = memberLevel;
        this.createTime = createTime;
        this.isRead = isRead;
        this.dynamicId = dynamicId;
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
