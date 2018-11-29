package com.ef.golf.pojo;

import java.util.Date;

public class Logs {
    private Long id;

    private Date createTime;

    private String requestUrl;

    private String requestParameter;

    private String serverAddr;

    private String remoteAddr;

    private String exception;

    private String syslogTitle;

    private String syslogMethods;

    private String syslogDescription;

    private String args;

    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    public String getSyslogTitle() {
        return syslogTitle;
    }

    public void setSyslogTitle(String syslogTitle) {
        this.syslogTitle = syslogTitle == null ? null : syslogTitle.trim();
    }

    public String getSyslogMethods() {
        return syslogMethods;
    }

    public void setSyslogMethods(String syslogMethods) {
        this.syslogMethods = syslogMethods == null ? null : syslogMethods.trim();
    }

    public String getSyslogDescription() {
        return syslogDescription;
    }

    public void setSyslogDescription(String syslogDescription) {
        this.syslogDescription = syslogDescription == null ? null : syslogDescription.trim();
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}