package com.ef.golf.pojo;

import java.util.Date;

public class Report {
    private Integer reportId;

    private Integer userId;

    private Integer bereportedUserId;

    private String type;

    private String cause;

    private Date createTime;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBereportedUserId() {
        return bereportedUserId;
    }

    public void setBereportedUserId(Integer bereportedUserId) {
        this.bereportedUserId = bereportedUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}