package com.ef.golf.vo;

import java.util.Date;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/5/5 16:53
 */
public class ByBlackListVo {
    private Long userId; //被拉黑人ID
    private String createTime;//拉黑时间
    private String headPortraitUrl;//被拉黑人头像
    private String userType;//被拉黑人用户类型
    private String nickName;//被拉黑人昵称
    private String realname;//被拉黑人真实姓名
    private String sex;//性别


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
