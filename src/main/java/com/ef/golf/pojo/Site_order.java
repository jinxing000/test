package com.ef.golf.pojo;

import java.util.Date;

public class Site_order {
    private Integer siteOrderId;

    private Integer siteId;

    private Integer orderId;

    private String playDate;

    private String playTime;

    private String playName;

    private String contactsPhone;

    private Integer siteMemberNumber;

    private Integer efMemberNumber;

    private Integer guestNumber;

    private Integer customerNumber;

    private String remark;

    private Integer ticketId;

    private String weekdaysEfMemberPrice;

    private String weekdaysSiteMemberPrice;

    private String weekdaysCustomerPrice;

    private String holidayEfMemberPrice;

    private String holidaySiteMemberPrice;

    private String holidayCustomerPrice;

    private Double payMoney;

    private Double realPayMoney;

    private Date createTime;

    private Date modifyTime;

    private String createUser;

    private String modifyUser;

    public Integer getSiteOrderId() {
        return siteOrderId;
    }

    public void setSiteOrderId(Integer siteOrderId) {
        this.siteOrderId = siteOrderId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate == null ? null : playDate.trim();
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime == null ? null : playTime.trim();
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName == null ? null : playName.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public Integer getSiteMemberNumber() {
        return siteMemberNumber;
    }

    public void setSiteMemberNumber(Integer siteMemberNumber) {
        this.siteMemberNumber = siteMemberNumber;
    }

    public Integer getEfMemberNumber() {
        return efMemberNumber;
    }

    public void setEfMemberNumber(Integer efMemberNumber) {
        this.efMemberNumber = efMemberNumber;
    }

    public Integer getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(Integer guestNumber) {
        this.guestNumber = guestNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getWeekdaysEfMemberPrice() {
        return weekdaysEfMemberPrice;
    }

    public void setWeekdaysEfMemberPrice(String weekdaysEfMemberPrice) {
        this.weekdaysEfMemberPrice = weekdaysEfMemberPrice == null ? null : weekdaysEfMemberPrice.trim();
    }

    public String getWeekdaysSiteMemberPrice() {
        return weekdaysSiteMemberPrice;
    }

    public void setWeekdaysSiteMemberPrice(String weekdaysSiteMemberPrice) {
        this.weekdaysSiteMemberPrice = weekdaysSiteMemberPrice == null ? null : weekdaysSiteMemberPrice.trim();
    }

    public String getWeekdaysCustomerPrice() {
        return weekdaysCustomerPrice;
    }

    public void setWeekdaysCustomerPrice(String weekdaysCustomerPrice) {
        this.weekdaysCustomerPrice = weekdaysCustomerPrice == null ? null : weekdaysCustomerPrice.trim();
    }

    public String getHolidayEfMemberPrice() {
        return holidayEfMemberPrice;
    }

    public void setHolidayEfMemberPrice(String holidayEfMemberPrice) {
        this.holidayEfMemberPrice = holidayEfMemberPrice == null ? null : holidayEfMemberPrice.trim();
    }

    public String getHolidaySiteMemberPrice() {
        return holidaySiteMemberPrice;
    }

    public void setHolidaySiteMemberPrice(String holidaySiteMemberPrice) {
        this.holidaySiteMemberPrice = holidaySiteMemberPrice == null ? null : holidaySiteMemberPrice.trim();
    }

    public String getHolidayCustomerPrice() {
        return holidayCustomerPrice;
    }

    public void setHolidayCustomerPrice(String holidayCustomerPrice) {
        this.holidayCustomerPrice = holidayCustomerPrice == null ? null : holidayCustomerPrice.trim();
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Double getRealPayMoney() {
        return realPayMoney;
    }

    public void setRealPayMoney(Double realPayMoney) {
        this.realPayMoney = realPayMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }
}