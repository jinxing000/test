package com.ef.golf.vo;


/**
 * create by xzw
 * 2018年1月9日17:24:27
 * 保存动态评论的信息
 */
public class QuanziCommentVo {
    private String dynamicComId;//评论id
    private String commentUserId;//评论人id
    private String replyUserId;//回复者id
    private String commentName;//评论人名字
    private String replyName;//回复者名字
    private String content;//评论内容

    public String getDynamicComId() {
        return dynamicComId;
    }

    public void setDynamicComId(String dynamicComId) {
        this.dynamicComId = dynamicComId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
