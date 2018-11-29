package com.ef.golf.vo;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/4/27 12:01
 * 教练订单列表
 */
public class CoachOrderListVo {

    private String headPortraitUrl;//头像
    private Integer orderId;//订单id
    private String orderNo;//订单编号
    private String createTime;//下单时间
    private String orderState;//订单状态
    private String modifyUser;//修改人
    private String modifyTime;//修改时间
    private Double orderMoney;//订单金额
    private String ticketId;//优惠券id
    private Integer coachId;//教练id
    private String coachName;//教练名
    private String qiuHuiId;//球会id
    private String qiuHuiName;//球会名
    private String playName;//打球人名字
    private String playDate;//打球日期
    private String playTime;//打球时间
    private String contactsPhone;//打球人联系方式
    private String ticketName;
    private String remark;//备注

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public String getQiuHuiName() {
        return qiuHuiName;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
