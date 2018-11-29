package com.ef.golf.vo;

/**
 * for efgolf
 * Created by Bart on 2017/9/25.
 * Date: 2017/9/25 14:46
 */
public class UserVo {

    private String userId;//用户id

    private String uid;//用户名

    private String sid;//会话id


    public UserVo() {
    }

public UserVo(String userId,String uid, String sid) {
        this.userId=userId;
        this.uid = uid;
        this.sid = sid;
    }
    public UserVo(String uid, String sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
