package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/4/27 16:30
 */
public class UserFeedback {

    private Integer id;
    private Integer userId;
    private String userFeedback;
    private Date createTime;

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

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
