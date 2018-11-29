package com.ef.golf.pojo;

import java.util.Date;

public class Dynamic_comment {
    private Integer dynamicComId;

    private Integer commentsUserId;

    private Integer replyUserId;

    private Integer dynamicId;

    private Date createTime;

    private String content;

    private String isDel;

    public Integer getDynamicComId() {
        return dynamicComId;
    }

    public void setDynamicComId(Integer dynamicComId) {
        this.dynamicComId = dynamicComId;
    }

    public Integer getCommentsUserId() {
        return commentsUserId;
    }

    public void setCommentsUserId(Integer commentsUserId) {
        this.commentsUserId = commentsUserId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
}