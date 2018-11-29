package com.ef.golf.vo;

import java.util.Date;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/5/9 10:27
 */
public class RedPackageDetailsVo {


    private Integer fId;//发红包人id
    private String headPortraitUrl;//发红包人头像
    private String nickName;//发红包人昵称
    private Integer count;//红包个数
    private String content;//红包祝福语
    private Integer receiverId;//抢红包人ID
    private Double smallMoneyAmount;//抢到红包金额
    private Date sendTime;//红包发送时间

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Double getSmallMoneyAmount() {
        return smallMoneyAmount;
    }

    public void setSmallMoneyAmount(Double smallMoneyAmount) {
        this.smallMoneyAmount = smallMoneyAmount;
    }

    public Integer getCount() {
        return count;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
