package com.ef.golf.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/6/7 14:07
 */
public class PushMsgListVo {
   private Integer workTypeId;
   private String workType;//事件类型(1 点赞 2 评论 3 回复 4 分享 5 待定)
   private Integer dynamicId;//动态id
   private String content;//消息内容
   private Integer workId;//操作人id
   private String headPortraitUrl;//操作人头像
   private String nickName;//操作人昵称
   private String userType;//操作人用户类型
   private String vip;//操作人级别
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date createTime;//操作时间
    private String readUnread;//已读未读 0未读 1已读
    private String gender;

    public PushMsgListVo() {
    }

    public PushMsgListVo(Integer workTypeId,String workType,Integer dynamicId, String content, Integer workId, String headPortraitUrl, String nickName, String userType, String vip, Date createTime) {
        this.workTypeId = workTypeId;
        this.workType = workType;
        this.dynamicId = dynamicId;
        this.content = content;
        this.workId = workId;
        this.headPortraitUrl = headPortraitUrl;
        this.nickName = nickName;
        this.userType = userType;
        this.vip = vip;
        this.createTime = createTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReadUnread() {
        return readUnread;
    }

    public void setReadUnread(String readUnread) {
        this.readUnread = readUnread;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
