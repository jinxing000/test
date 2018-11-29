package com.ef.golf.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/5/10 11:57
 */
public class TicketSend implements Serializable {

    private Integer ticketZzId;//优惠券转赠Id
    private Integer ticketId;//优惠券Id
    private Integer userId;//发优惠券人
    private Integer recieverId;//抢到优惠券人
    private Date createTime;//转赠时间
    private String sendType;//发放类型（1.个人到个人  2.群里发放）

    public Integer getTicketZzId() {
        return ticketZzId;
    }

    public void setTicketZzId(Integer ticketZzId) {
        this.ticketZzId = ticketZzId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Integer recieverId) {
        this.recieverId = recieverId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
