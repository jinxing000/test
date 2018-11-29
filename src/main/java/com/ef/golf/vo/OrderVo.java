package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;

import java.util.Date;
import java.util.List;

/**
 * for golf
 * Created by Administrator on 2017/10/16.
 * Date: 2017/10/16 15:21
 * 订单
 */
public class OrderVo extends Page {

    private int orderId;//订单id
    private String imgUrl;//商品图片url
    private int accountId;//账户id
    private String orderNo;//订单编号
    private String name;//订单的名称
    private String orderState;//订单状态
    private String orderType;//订单类型
    private double orderMoney;//订单金额
    private Date createTime;//下单时间
    private Date modifyTime;//修改时间

    public int getOrderId() {
        return orderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
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
}
