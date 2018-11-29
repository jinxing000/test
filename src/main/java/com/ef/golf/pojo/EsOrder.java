package com.ef.golf.pojo;

import com.ef.golf.util.NotDbField;
import com.ef.golf.util.OrderStatus;
import com.ef.golf.vo.EsOrderVo;


import java.math.BigDecimal;
import java.util.List;

public class EsOrder {
    private Integer orderId;

    private String sn;

    private Integer memberId;

    private Integer status;

    private Integer payStatus;

    private Integer shipStatus;

    private Integer shippingId;

    private String shippingType;

    private String shippingArea;

    private Integer paymentId;

    private String paymentName;

    private String paymentType;

    private String paymentAccount;

    private Double paymoney;

    private Long createTime;

    private String shipName;

    private String shipAddr;

    private String shipZip;

    private String shipEmail;

    private String shipMobile;

    private String shipTel;

    private String shipDay;

    private String shipTime;

    private String isProtect;

    private Double protectPrice;

    private Double goodsAmount;

    private Double shippingAmount;

    private Double orderAmount;

    private Double weight;

    private Integer goodsNum;

    private Integer gainedpoint;

    private Integer consumepoint;

    private String disabled;

    private Double discount;

    private Integer imported;

    private Integer pimported;

    private Integer completeTime;

    private String cancelReason;

    private Integer signingTime;

    private String theSign;

    private Integer allocationTime;

    private Integer shipProvinceid;

    private Integer shipCityid;

    private Integer shipRegionid;

    private Integer shipTownid;

    private Integer saleCmpl;

    private Integer saleCmplTime;

    private Integer depotid;

    private String adminRemark;

    private Double needPayMoney;

    private String shipNo;

    private Integer addressId;

    private Integer logiId;

    private String logiName;

    private Integer giftId;

    private Integer bonusId;

    private Double actDiscount;

    private Integer activityPoint;

    private Integer isCancel;

    private Integer isOnline;

    private String receiptContent;

    private String receiptTitle;

    private String receiptDuty;

    private Integer receiptType;

    private Integer receipt;//1.需要发票 2.不需要

    private String goods;

    private String remark;

    private String itemsJson;

    private String orderStatus;


    //订单的价格，非数据库字段
    private OrderPrice orderprice;

    List<EsOrderVo> orderVoList;
    @NotDbField
    public List<EsOrderVo> getOrderVoList() {
        return orderVoList;
    }

    public void setOrderVoList(List<EsOrderVo> orderVoList) {
        this.orderVoList = orderVoList;
    }

    @NotDbField
    public OrderPrice getOrderprice() {
        //如果价格为空，返回价格为空，但是不为null
        if(orderprice == null ){
            return new OrderPrice();
        }
        return orderprice;
    }



    /**
     * 订单价格设置<br>
     * 根据订单价格设置此订单的商品价、运费、重量、优惠金额等
     * @param orderPrice
     */
    public void setOrderprice(OrderPrice orderPrice) {

        this.setGoodsAmount( Double.parseDouble(new BigDecimal(orderPrice.getGoodsPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
        this.setWeight( Double.parseDouble(new BigDecimal(orderPrice.getWeight()).toString()));
        this.setOrderAmount( Double.parseDouble(new BigDecimal(orderPrice.getOrderPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString()));
        this.setProtectPrice( Double.parseDouble(new BigDecimal(0d).toString()));
        this.setShippingAmount( Double.parseDouble(new BigDecimal(orderPrice.getShippingPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString()));
        this.setGainedpoint(orderPrice.getPoint());
        this.setDiscount( Double.parseDouble(new BigDecimal(orderPrice.getDiscountPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString()));
        this.setNeedPayMoney( Double.parseDouble(new BigDecimal(orderPrice.getNeedPayMoney()).setScale(2,BigDecimal.ROUND_HALF_UP).toString()));
        this.setGiftId(orderPrice.getGift_id());	//添加赠品ID add by DMRain 2016-1-15
        this.setBonusId(orderPrice.getBonus_id());	//添加优惠券ID add by DMRain 2016-1-20
        this.setActDiscount( Double.parseDouble(new BigDecimal(orderPrice.getActDiscount()).setScale(2,BigDecimal.ROUND_HALF_UP).toString())); //添加促销活动优惠的现金金额 add by DMRain 2016-6-20
       // this.setActivityPoint(orderPrice.getActivity_point());	//添加促销活动赠送的积分 add by DMRain 2016-7-22
        this.orderprice = orderPrice;
    }






    @NotDbField
    public String getOrderStatus() {
        if(status==null) return null;
        orderStatus = OrderStatus.getOrderStatusText(status);
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }



    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getItemsJson() {
        return itemsJson;
    }

    public void setItemsJson(String itemsJson) {
        this.itemsJson = itemsJson == null ? null : itemsJson.trim();
    }





    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Integer shipStatus) {
        this.shipStatus = shipStatus;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType == null ? null : shippingType.trim();
    }

    public String getShippingArea() {
        return shippingArea;
    }

    public void setShippingArea(String shippingArea) {
        this.shippingArea = shippingArea == null ? null : shippingArea.trim();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName == null ? null : paymentName.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount == null ? null : paymentAccount.trim();
    }

    public Double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Double paymoney) {
        this.paymoney = paymoney;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr == null ? null : shipAddr.trim();
    }

    public String getShipZip() {
        return shipZip;
    }

    public void setShipZip(String shipZip) {
        this.shipZip = shipZip == null ? null : shipZip.trim();
    }

    public String getShipEmail() {
        return shipEmail;
    }

    public void setShipEmail(String shipEmail) {
        this.shipEmail = shipEmail == null ? null : shipEmail.trim();
    }

    public String getShipMobile() {
        return shipMobile;
    }

    public void setShipMobile(String shipMobile) {
        this.shipMobile = shipMobile == null ? null : shipMobile.trim();
    }

    public String getShipTel() {
        return shipTel;
    }

    public void setShipTel(String shipTel) {
        this.shipTel = shipTel == null ? null : shipTel.trim();
    }

    public String getShipDay() {
        return shipDay;
    }

    public void setShipDay(String shipDay) {
        this.shipDay = shipDay == null ? null : shipDay.trim();
    }

    public String getShipTime() {
        return shipTime;
    }

    public void setShipTime(String shipTime) {
        this.shipTime = shipTime == null ? null : shipTime.trim();
    }

    public String getIsProtect() {
        return isProtect;
    }

    public void setIsProtect(String isProtect) {
        this.isProtect = isProtect == null ? null : isProtect.trim();
    }

    public Double getProtectPrice() {
        return protectPrice;
    }

    public void setProtectPrice(Double protectPrice) {
        this.protectPrice = protectPrice;
    }

    public Double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getGainedpoint() {
        return gainedpoint;
    }

    public void setGainedpoint(Integer gainedpoint) {
        this.gainedpoint = gainedpoint;
    }

    public Integer getConsumepoint() {
        return consumepoint;
    }

    public void setConsumepoint(Integer consumepoint) {
        this.consumepoint = consumepoint;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getImported() {
        return imported;
    }

    public void setImported(Integer imported) {
        this.imported = imported;
    }

    public Integer getPimported() {
        return pimported;
    }

    public void setPimported(Integer pimported) {
        this.pimported = pimported;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Integer getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Integer signingTime) {
        this.signingTime = signingTime;
    }

    public String getTheSign() {
        return theSign;
    }

    public void setTheSign(String theSign) {
        this.theSign = theSign == null ? null : theSign.trim();
    }

    public Integer getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(Integer allocationTime) {
        this.allocationTime = allocationTime;
    }

    public Integer getShipProvinceid() {
        return shipProvinceid;
    }

    public void setShipProvinceid(Integer shipProvinceid) {
        this.shipProvinceid = shipProvinceid;
    }

    public Integer getShipCityid() {
        return shipCityid;
    }

    public void setShipCityid(Integer shipCityid) {
        this.shipCityid = shipCityid;
    }

    public Integer getShipRegionid() {
        return shipRegionid;
    }

    public void setShipRegionid(Integer shipRegionid) {
        this.shipRegionid = shipRegionid;
    }

    public Integer getShipTownid() {
        return shipTownid;
    }

    public void setShipTownid(Integer shipTownid) {
        this.shipTownid = shipTownid;
    }

    public Integer getSaleCmpl() {
        return saleCmpl;
    }

    public void setSaleCmpl(Integer saleCmpl) {
        this.saleCmpl = saleCmpl;
    }

    public Integer getSaleCmplTime() {
        return saleCmplTime;
    }

    public void setSaleCmplTime(Integer saleCmplTime) {
        this.saleCmplTime = saleCmplTime;
    }

    public Integer getDepotid() {
        return depotid;
    }

    public void setDepotid(Integer depotid) {
        this.depotid = depotid;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark == null ? null : adminRemark.trim();
    }

    public Double getNeedPayMoney() {
        return needPayMoney;
    }

    public void setNeedPayMoney(Double needPayMoney) {
        this.needPayMoney = needPayMoney;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo == null ? null : shipNo.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getLogiId() {
        return logiId;
    }

    public void setLogiId(Integer logiId) {
        this.logiId = logiId;
    }

    public String getLogiName() {
        return logiName;
    }

    public void setLogiName(String logiName) {
        this.logiName = logiName == null ? null : logiName.trim();
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

    public Double getActDiscount() {
        return actDiscount;
    }

    public void setActDiscount(Double actDiscount) {
        this.actDiscount = actDiscount;
    }

    public Integer getActivityPoint() {
        return activityPoint;
    }

    public void setActivityPoint(Integer activityPoint) {
        this.activityPoint = activityPoint;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getReceiptContent() {
        return receiptContent;
    }

    public void setReceiptContent(String receiptContent) {
        this.receiptContent = receiptContent == null ? null : receiptContent.trim();
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle == null ? null : receiptTitle.trim();
    }

    public String getReceiptDuty() {
        return receiptDuty;
    }

    public void setReceiptDuty(String receiptDuty) {
        this.receiptDuty = receiptDuty == null ? null : receiptDuty.trim();
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Integer getReceipt() {
        return receipt;
    }

    public void setReceipt(Integer receipt) {
        this.receipt = receipt;
    }
}