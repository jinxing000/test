package com.ef.golf.pojo;

import java.util.Date;

public class Tickets {
    private Integer ticketId;//优惠券id

    private Double money;//面额减免上限 暂废

    private Double discount;//折扣

    private Integer conditions;//使用条件（默认0）

    private Integer type;//优惠券类型（1.折扣 2.满减或现金）

    private Integer attribution;//优惠券归属（1.通用 2.场地 3.教练 4.球童 5.球队 6.赛事 7.商家 8.商城 9.旅游 0.课程）

    private Integer productId;

    private Byte isPublic;//是否为公用优惠券(1.公用 2.个人)

    private Integer effectiveDate;//失效天数

    private Date effectiveTime;//生效日期

    private Date expiryTime;//失效日期

    private Date createTime;//创建时间

    private Date modifyTime;//修改时间

    private String createUser;//创建人

    private String modifyUser;//修改人

    private String ticketName;//优惠券名

    private Double discountMoney;//折扣上限

    private String hasDoor;//有无门槛（1是0否）

    private Double doorMoney;//门槛金额

    private Double specialMoney;//优惠金额

    private Integer ticketNum;//优惠劵数量

    private String backCash;//是否全额退款(是 1，否 0)

    private String isGrant;//是否已发放（1是，0否）

    private String isDue;//优惠劵是否使用（0，过期，1已使用 ，2未使用）

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Byte getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Integer effectiveDate) {
        this.effectiveDate = effectiveDate;
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

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public Double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(String hasDoor) {
        this.hasDoor = hasDoor == null ? null : hasDoor.trim();
    }

    public Double getDoorMoney() {
        return doorMoney;
    }

    public void setDoorMoney(Double doorMoney) {
        this.doorMoney = doorMoney;
    }

    public Double getSpecialMoney() {
        return specialMoney;
    }

    public void setSpecialMoney(Double specialMoney) {
        this.specialMoney = specialMoney;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getBackCash() {
        return backCash;
    }

    public void setBackCash(String backCash) {
        this.backCash = backCash == null ? null : backCash.trim();
    }

    public String getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(String isGrant) {
        this.isGrant = isGrant == null ? null : isGrant.trim();
    }

    public String getIsDue() {
        return isDue;
    }

    public void setIsDue(String isDue) {
        this.isDue = isDue == null ? null : isDue.trim();
    }
}