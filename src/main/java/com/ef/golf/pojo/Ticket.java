package com.ef.golf.pojo;

import java.util.Date;

public class Ticket {
    private Integer ticketId;

    private Long userId;

    private Integer state;//状态（1未发放、2未到期、3未使用、4已使用、5已失效）

    private String ticketName;

    private Double money;//面额减免上限 废弃

    private Double specialMoney;//优惠金额

    private Double discount;//折扣

    private Integer conditions;//使用条件（默认0）

    private Integer type;//优惠券类型（1.折扣 2.满减或现金）

    private Integer attribution;//优惠券归属（1.通用 2.场地 3.教练 4.球童 5.球队 6.赛事 7.商家 8.商城 9.旅游 0.课程）

    private Date effectiveTime;//生效日期

    private Date expiryTime;//失效日期

    private Date createTime;//创建时间

    private Date modifyTime;//修改时间

    private String createUser;//创建人

    private String modifyUser;//修改人

    public Double getSpecialMoney() {
        return specialMoney;
    }

    public void setSpecialMoney(Double specialMoney) {
        this.specialMoney = specialMoney;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
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