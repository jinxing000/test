package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;

import java.io.Serializable;

/**
 * 教练工作区订单
 */
public class CoachWorkOrderVo{

   /* private Integer userId;*/
    private Integer orderId;//订单id
    private String orderNo;//订单编号
    private String createTime;//下单时间
    private String playName;//预约人姓名
    private String playDate;//打球日期
    private String playTime;//打球时间
    private String orderState;//订单状态
    private String modifyUser;//修改人
    private String modifyTime;//修改时间
    private String orderType;//订单类型

    public CoachWorkOrderVo() {
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

   /* public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public CoachWorkOrderVo(/*Integer userId,*/ Integer orderId, String orderNo, String createTime, String playName, String playDate, String playTime, String orderState) {
        /*this.userId = userId;*/
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.playName = playName;
        this.playDate = playDate;
        this.playTime = playTime;
        this.orderState = orderState;
    }
}