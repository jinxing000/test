package com.ef.golf.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 球场订单详情
 */
public class SiteOrderDatailVo extends OrderVo implements Serializable {
    private Integer siteId;//球场id
    private String imgUrl;//球场图片url
    private String orderType;//订单类型
    private String play_name;//打球人姓名
    private String  siteName;//球场名称
    private Date  play_date;//打球日期
    private String  play_time;//打球时间
    private String contacts_phone;//联系电话
    private String  ef_member_number;//
    private String customer_number;
    private String guest_number;
    private String site_member_number;
    private Integer sumMember;//打球总人数
    private Integer ticketType;//优惠券类型
    private Double money;//支付金额
    private Integer conditions;//使用条件
    private Date effective_time;//生效时间
    private Date expiry_time;//失效时间
    private String remark;//备注
    private Double  pay_money;//应付价格

    public SiteOrderDatailVo(){}


    public SiteOrderDatailVo(Integer siteId,String imgUrl, String orderType, String play_name, String siteName, Date play_date, String play_time, String contacts_phone, String ef_member_number, String customer_number, String guest_number, String site_member_number, Integer sumMember, Integer ticketType, Double money, Integer conditions, Date effective_time, Date expiry_time, String remark, Double pay_money, Double real_pay_money) {
        this.siteId=siteId;
        this.imgUrl = imgUrl;
        this.orderType = orderType;
        this.play_name = play_name;
        this.siteName = siteName;
        this.play_date = play_date;
        this.play_time = play_time;
        this.contacts_phone = contacts_phone;
        this.ef_member_number = ef_member_number;
        this.customer_number = customer_number;
        this.guest_number = guest_number;
        this.site_member_number = site_member_number;
        this.sumMember = sumMember;
        this.ticketType = ticketType;
        this.money = money;
        this.conditions = conditions;
        this.effective_time = effective_time;
        this.expiry_time = expiry_time;
        this.remark = remark;
        this.pay_money = pay_money;
        this.real_pay_money = real_pay_money;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPlay_name() {
        return play_name;
    }

    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Date getPlay_date() {
        return play_date;
    }

    public void setPlay_date(Date play_date) {
        this.play_date = play_date;
    }

    public String getPlay_time() {
        return play_time;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }

    public String getEf_member_number() {
        return ef_member_number;
    }

    public void setEf_member_number(String ef_member_number) {
        this.ef_member_number = ef_member_number;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getGuest_number() {
        return guest_number;
    }

    public void setGuest_number(String guest_number) {
        this.guest_number = guest_number;
    }

    public String getSite_member_number() {
        return site_member_number;
    }

    public void setSite_member_number(String site_member_number) {
        this.site_member_number = site_member_number;
    }

    public Integer getSumMember() {
        return sumMember;
    }

    public void setSumMember(Integer sumMember) {
        this.sumMember = sumMember;
    }

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getConditions() {
        return conditions;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    public Date getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(Date effective_time) {
        this.effective_time = effective_time;
    }

    public Date getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Date expiry_time) {
        this.expiry_time = expiry_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    public Double getReal_pay_money() {
        return real_pay_money;
    }

    public void setReal_pay_money(Double real_pay_money) {
        this.real_pay_money = real_pay_money;
    }

    private Double  real_pay_money;//实付价格









}
