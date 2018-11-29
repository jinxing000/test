package com.ef.golf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/6/5 19:35
 */
public class WorkType {
   private Long id;
   private Long userId;
   private String type;//事件类型(1 点赞 2 评论 3 回复 4 分享 5 待定)
   private Long productId;
   private String readUnread;//true 已读 false未读
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date createTime;
   private String bigType;
   private Integer orderId;

    public WorkType() {
    }

    public WorkType(Long id, Long userId, String type, Long productId, String readUnread, Date createTime,String bigType,Integer orderId) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.productId = productId;
        this.readUnread = readUnread;
        this.createTime = createTime;
        this.bigType = bigType;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadUnread() {
        return readUnread;
    }

    public void setReadUnread(String readUnread) {
        this.readUnread = readUnread;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
