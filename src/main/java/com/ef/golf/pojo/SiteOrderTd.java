package com.ef.golf.pojo;

public class SiteOrderTd {
    private Integer id;

    private Integer siteOrderId;

    private Integer memberTdNumber;

    private Integer guestTdNumber;

    private Double refundMoney;

    private Double realMoney;

    private String tdReason;

    private String orderNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteOrderId() {
        return siteOrderId;
    }

    public void setSiteOrderId(Integer siteOrderId) {
        this.siteOrderId = siteOrderId;
    }

    public Integer getMemberTdNumber() {
        return memberTdNumber;
    }

    public void setMemberTdNumber(Integer memberTdNumber) {
        this.memberTdNumber = memberTdNumber;
    }

    public Integer getGuestTdNumber() {
        return guestTdNumber;
    }

    public void setGuestTdNumber(Integer guestTdNumber) {
        this.guestTdNumber = guestTdNumber;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Double realMoney) {
        this.realMoney = realMoney;
    }

    public String getTdReason() {
        return tdReason;
    }

    public void setTdReason(String tdReason) {
        this.tdReason = tdReason == null ? null : tdReason.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }
}