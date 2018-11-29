package com.ef.golf.vo;

public class ActivityMessageVo {

    private String activityId;
    private String templateId;
    private String couponNum;
    private String typeOne;
    private String typeTwo;
    private String reserved2;
    private String reserved3;

    public ActivityMessageVo() {

    }

    public ActivityMessageVo(String activityId, String templateId, String couponNum, String typeOne, String typeTwo, String reserved2, String reserved3) {
        this.activityId = activityId;
        this.templateId = templateId;
        this.couponNum = couponNum;
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.reserved2 = reserved2;
        this.reserved3 = reserved3;
    }

    public String getTypeOne() {
        return typeOne;
    }

    public void setTypeOne(String typeOne) {
        this.typeOne = typeOne;
    }

    public String getTypeTwo() {
        return typeTwo;
    }

    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }


}
