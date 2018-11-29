package com.ef.golf.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/6/21 15:15
 */
public class InfromMsgVo {

  private Integer  infromId;//id
  private String  readUnread;//已读未读 0 未读 1已读
  private String  userId;//操作人id
  private String  title;//标题
  private String  content;//内容
  private String  url;//url
  private String icoUrl;//图标
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    public InfromMsgVo() {
    }

    public InfromMsgVo(Integer infromId, String readUnread, String userId, String title, String content, String url,String icourl,Date createTime) {
        this.infromId = infromId;
        this.readUnread = readUnread;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.icoUrl = icourl;
        this.createTime = createTime;
    }

    public Integer getInfromId() {
        return infromId;
    }

    public void setInfromId(Integer infromId) {
        this.infromId = infromId;
    }

    public String getReadUnread() {
        return readUnread;
    }

    public void setReadUnread(String readUnread) {
        this.readUnread = readUnread;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
