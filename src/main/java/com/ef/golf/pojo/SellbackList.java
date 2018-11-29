package com.ef.golf.pojo;

import java.math.BigDecimal;

public class SellbackList {
    private Integer id;

    private Integer orderid;

    private String tradeno;

    private Integer tradestatus;

    private String ordersn;

    private String regoperator;

    private Long regtime;

    private BigDecimal alltotalPay;

    private BigDecimal applyAlltotal;

    private String goodslist;

    private String remark;

    private String sellerRemark;

    private String warehouseRemark;

    private String financeRemark;

    private Integer memberId;

    private String sndto;

    private String tel;

    private String adr;

    private String zip;

    private String refundWay;

    private String total;

    private Integer depotid;

    private String returnAccount;

    private String reason;

    private Integer type;

    private Long confirmTime;

    private Integer giftId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    public Integer getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(Integer tradestatus) {
        this.tradestatus = tradestatus;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn == null ? null : ordersn.trim();
    }

    public String getRegoperator() {
        return regoperator;
    }

    public void setRegoperator(String regoperator) {
        this.regoperator = regoperator == null ? null : regoperator.trim();
    }

    public Long getRegtime() {
        return regtime;
    }

    public void setRegtime(Long regtime) {
        this.regtime = regtime;
    }

    public BigDecimal getAlltotalPay() {
        return alltotalPay;
    }

    public void setAlltotalPay(BigDecimal alltotalPay) {
        this.alltotalPay = alltotalPay;
    }

    public BigDecimal getApplyAlltotal() {
        return applyAlltotal;
    }

    public void setApplyAlltotal(BigDecimal applyAlltotal) {
        this.applyAlltotal = applyAlltotal;
    }

    public String getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(String goodslist) {
        this.goodslist = goodslist == null ? null : goodslist.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark == null ? null : sellerRemark.trim();
    }

    public String getWarehouseRemark() {
        return warehouseRemark;
    }

    public void setWarehouseRemark(String warehouseRemark) {
        this.warehouseRemark = warehouseRemark == null ? null : warehouseRemark.trim();
    }

    public String getFinanceRemark() {
        return financeRemark;
    }

    public void setFinanceRemark(String financeRemark) {
        this.financeRemark = financeRemark == null ? null : financeRemark.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getSndto() {
        return sndto;
    }

    public void setSndto(String sndto) {
        this.sndto = sndto == null ? null : sndto.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr == null ? null : adr.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay == null ? null : refundWay.trim();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public Integer getDepotid() {
        return depotid;
    }

    public void setDepotid(Integer depotid) {
        this.depotid = depotid;
    }

    public String getReturnAccount() {
        return returnAccount;
    }

    public void setReturnAccount(String returnAccount) {
        this.returnAccount = returnAccount == null ? null : returnAccount.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Long confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }
}