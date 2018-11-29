package com.ef.golf.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketVo {

    private Integer ticketId;

    private String ticketName;//优惠券名字

    private Double money;//面额减免税上限

    private Double discount;//折扣

    private Integer conditions;//使用条件

    private Integer type;//优惠券类型

    private Integer attribution;//优惠券归属

    private Date effectiveTime;//生效日期

    private Date expiryTime;//失效日期


    public TicketVo(){}


    public TicketVo(Integer ticketId,String ticketName,Double money,Double discount, Integer conditions, Integer type, Integer attribution, Date effectiveTime, Date expiryTime) {
        this.ticketId=ticketId;
        this.ticketName=ticketName;
        this.discount=discount;
        this.money = money;
        this.conditions = conditions;
        this.type = type;
        this.attribution = attribution;
        this.effectiveTime = effectiveTime;
        this.expiryTime = expiryTime;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getConditions() {
        return conditions;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAttribution() {
        return attribution;
    }

    public void setAttribution(Integer attribution) {
        this.attribution = attribution;
    }

    public Date getEffectiveTime() {

        return effectiveTime;
    }



    public void setEffectiveTime(Date effectiveTime) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=sdf.parse(sdf.format(effectiveTime));
        this.effectiveTime = d;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
