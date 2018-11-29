package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * 球童订单
 *
 */
public class QiuTongWorkOrderVo extends Page implements Serializable{

   private Integer orderId;//id
   private String orderNo;//订单编号
   private Double orderMoney;//订单金额
   private String createTime;//下单时间
   private String orderState;//订单状态
   private Integer qiuTongOrderId;//球童订单id
   private Integer qiutongId;//球童id
   private String playName;//打球人姓名
   private String contactsPhone;//打球人联系方式
   private String playDate;//打球日期
   private String playTime;//打球时间
   private String remark;//备注
   private Integer qiuHuiId;//球会id
   private String   qiuHuiName;//球会名字
   private String modifyUser;//修改人
   private Date modifyTime;//修改时间

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

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

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Integer getQiuTongOrderId() {
        return qiuTongOrderId;
    }

    public void setQiuTongOrderId(Integer qiuTongOrderId) {
        this.qiuTongOrderId = qiuTongOrderId;
    }

    public Integer getQiutongId() {
        return qiutongId;
    }

    public void setQiutongId(Integer qiutongId) {
        this.qiutongId = qiutongId;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(Integer qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public String getQiuHuiName() {
        return qiuHuiName;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public QiuTongWorkOrderVo() {
    }

    public QiuTongWorkOrderVo(Integer orderId, String orderNo, Double orderMoney, String createTime, String orderState, Integer qiuTongOrderId, Integer qiutongId, String playName, String contactsPhone, String playDate, String playTime, String remark, Integer qiuHuiId, String qiuHuiName, String modifyUser, Date modifyTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
        this.createTime = createTime;
        this.orderState = orderState;
        this.qiuTongOrderId = qiuTongOrderId;
        this.qiutongId = qiutongId;
        this.playName = playName;
        this.contactsPhone = contactsPhone;
        this.playDate = playDate;
        this.playTime = playTime;
        this.remark = remark;
        this.qiuHuiId = qiuHuiId;
        this.qiuHuiName = qiuHuiName;
        this.modifyUser = modifyUser;
        this.modifyTime = modifyTime;
    }
}
