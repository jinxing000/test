package com.ef.golf.vo;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/4/27 9:07
 * 球场订单列表
 */
public class SiteOrderListVo {

    private String img_url;//场地图片
    private Integer orderId;//订单id
    private String orderNo;//订单编号
    private String createTime;//下单时间
    private String orderState;//订单状态
    private String modifyUser;//修改人
    private String modifyTime;//修改时间
    private String orderMoeny;//合计订单金额moeny
    private String orderMoney;//合计订单金额money
    private String ticketId;//优惠券id
    private Integer siteId;//球场Id
    private String siteName;//球场名称
    private String siteParam;//球场数据
    private String reserveExplain;//预约说明
    private String cancelExplain;//取消通知说明
    private String playDate;//打球日期
    private String playTime;//打球时间
    private String contactsPhone;//打球人联系电话
    private String headcount;//总人数
    private String playName;//打球人
    private String memberUser;//球场会员
    private String ticketName;
    private String remark;//备注

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getOrderMoeny() {
        return orderMoeny;
    }

    public String getReserveExplain() {
        return reserveExplain;
    }

    public void setReserveExplain(String reserveExplain) {
        this.reserveExplain = reserveExplain;
    }

    public String getCancelExplain() {
        return cancelExplain;
    }

    public void setCancelExplain(String cancelExplain) {
        this.cancelExplain = cancelExplain;
    }

    public void setOrderMoeny(String orderMoeny) {
        this.orderMoeny = orderMoeny;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteParam() {
        return siteParam;
    }

    public void setSiteParam(String siteParam) {
        this.siteParam = siteParam;
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

    public String getHeadcount() {
        return headcount;
    }

    public void setHeadcount(String headcount) {
        this.headcount = headcount;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(String memberUser) {
        this.memberUser = memberUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }
}
