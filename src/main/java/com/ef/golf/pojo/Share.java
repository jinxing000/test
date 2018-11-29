package com.ef.golf.pojo;

import java.util.Date;

public class Share {
    private Integer shareId;

    private Integer shareUserId;

    private String shareType;

    private Integer shareDynamicId;

    private Date createTime;

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(Integer shareUserId) {
        this.shareUserId = shareUserId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public Integer getShareDynamicId() {
        return shareDynamicId;
    }

    public void setShareDynamicId(Integer shareDynamicId) {
        this.shareDynamicId = shareDynamicId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}