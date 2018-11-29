package com.ef.golf.vo;

import java.util.Date;

public class TicketSeVo {

    private Integer id;
    private Integer ticketId;
    private String ticketName;//优惠券名字
    private Double discount;//折扣
    private Integer type;//优惠券类型
    private Date effectiveTime;//生效日期
    private Date expiryTime;//失效日期
    private Integer state;
    private Double special_money;//满减券满减金额
    private Double discount_money;//打折券折扣金额上限
    private Integer fitterId;//场景专属 id
    private String fitterName;//场景名
    private Integer location;//归属地 优惠券归属（1.通用 2.场地 3.教练 4.球童 5.球队 6.赛事 7.商家 8.商城 9.旅游 0.课程）
    private  String has_door;//有无门槛  （1是0否）
    private Double door_money;//门槛金额

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Double getSpecial_money() {
        return special_money;
    }

    public void setSpecial_money(Double special_money) {
        this.special_money = special_money;
    }

    public Double getDiscount_money() {
        return discount_money;
    }

    public void setDiscount_money(Double discount_money) {
        this.discount_money = discount_money;
    }

    public Integer getFitterId() {
        return fitterId;
    }

    public void setFitterId(Integer fitterId) {
        this.fitterId = fitterId;
    }

    public String getFitterName() {
        return fitterName;
    }

    public void setFitterName(String fitterName) {
        this.fitterName = fitterName;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getHas_door() {
        return has_door;
    }

    public void setHas_door(String has_door) {
        this.has_door = has_door;
    }

    public Double getDoor_money() {
        return door_money;
    }

    public void setDoor_money(Double door_money) {
        this.door_money = door_money;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
