package com.ef.golf.pojo;

import java.math.BigDecimal;

public class ActivityDetail {
    private Integer detailId;

    private Integer activityId;

    private BigDecimal fullMoney;

    private BigDecimal minusValue;

    private Integer pointValue;

    private Short isFullMinus;

    private Short isFreeShip;

    private Short isSendPoint;

    private Short isSendGift;

    private Short isSendBonus;

    private Integer giftId;

    private Integer bonusId;

    private Short isDiscount;

    private BigDecimal discountValue;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    public BigDecimal getMinusValue() {
        return minusValue;
    }

    public void setMinusValue(BigDecimal minusValue) {
        this.minusValue = minusValue;
    }

    public Integer getPointValue() {
        return pointValue;
    }

    public void setPointValue(Integer pointValue) {
        this.pointValue = pointValue;
    }

    public Short getIsFullMinus() {
        return isFullMinus;
    }

    public void setIsFullMinus(Short isFullMinus) {
        this.isFullMinus = isFullMinus;
    }

    public Short getIsFreeShip() {
        return isFreeShip;
    }

    public void setIsFreeShip(Short isFreeShip) {
        this.isFreeShip = isFreeShip;
    }

    public Short getIsSendPoint() {
        return isSendPoint;
    }

    public void setIsSendPoint(Short isSendPoint) {
        this.isSendPoint = isSendPoint;
    }

    public Short getIsSendGift() {
        return isSendGift;
    }

    public void setIsSendGift(Short isSendGift) {
        this.isSendGift = isSendGift;
    }

    public Short getIsSendBonus() {
        return isSendBonus;
    }

    public void setIsSendBonus(Short isSendBonus) {
        this.isSendBonus = isSendBonus;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getBonusId() {
        return bonusId;
    }

    public void setBonusId(Integer bonusId) {
        this.bonusId = bonusId;
    }

    public Short getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Short isDiscount) {
        this.isDiscount = isDiscount;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
}