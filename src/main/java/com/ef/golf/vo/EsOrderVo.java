package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.pojo.TuiHuoAddress;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商城订单列表
 */
public class EsOrderVo extends Page{

    private int efOrderId;//主订单id 用于支付
    private int shopOrderId;//商城订单id
    private String sn;//订单号
    private int status;//订单状态
    private String createTime;//下单时间
    private Double orderMoney;//实付金额
    private String name;//收货人
    private String tel;//联系方式
    private Integer addressId;//地址id
    private String address;//收货地址
    private Double shippingAmount;//运费
    private String ticketName;//优惠券类型
    private String remark;//备注
    List<OrderItemVo> orderItemVoList;//订单下的商品项

    private String logiName;   //物流公司
    private String shipNo;   //物流号
    private String companyCode;//物流公司代码

    private TuiHuoAddress tuiHuoAddress;

    public int getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(int shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {

//返回的日期时间有毫秒 去除毫秒
        return createTime.substring(0,createTime.lastIndexOf("."));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public int getEfOrderId() {
        return efOrderId;
    }

    public void setEfOrderId(int efOrderId) {
        this.efOrderId = efOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public TuiHuoAddress getTuiHuoAddress() {
        return tuiHuoAddress;
    }

    public void setTuiHuoAddress(TuiHuoAddress tuiHuoAddress) {
        this.tuiHuoAddress = tuiHuoAddress;
    }

    public String getLogiName() {
        return logiName;
    }

    public void setLogiName(String logiName) {
        this.logiName = logiName;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}

