package com.ef.golf.vo;
/**
 * com.ef.golf.vo
 * Administrator
 * 2018/6/6 10:05
 */
public class PushWorkTypeVo {

    private String productId;//这里只有圈子的消息是动态id
    private String readUnread;
    private String type;
    private Long workUserId;
    private Long sendUserId;

    public PushWorkTypeVo() {
    }

    public PushWorkTypeVo(String productId, String readUnread,String type, Long workUserId, Long sendUserId) {
        this.productId = productId;
        this.readUnread = readUnread;
        this.type = type;
        this.workUserId = workUserId;
        this.sendUserId = sendUserId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReadUnread() {
        return readUnread;
    }

    public void setReadUnread(String readUnread) {
        this.readUnread = readUnread;
    }

    public Long getWorkUserId() {
        return workUserId;
    }

    public void setWorkUserId(Long workUserId) {
        this.workUserId = workUserId;
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
