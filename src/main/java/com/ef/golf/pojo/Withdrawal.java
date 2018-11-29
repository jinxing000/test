package com.ef.golf.pojo;

import java.util.Date;

public class Withdrawal {
    private Integer withdrawalId; //id
    private Integer accountId;//账户id
    private Double money;//提现金额
    private String state;//提现状态
    private Date createTime;//提现申请时间
    private Date modifyTime;//确认或者否定申请时间
    private Integer flowId;//流水id
    private String orderNo;//单号
    private Double realMoey;//最终到账金额
    private Double serviceCharge;//扣除服务费
    private String remark;//备注
    private String bankCard;//银行卡号
    private String bankName;//开户行
    private String iderName;//持卡人姓名
    private String pingId;//ping++ withdrawalId

    public String getPingId() {
        return pingId;
    }

    public void setPingId(String pingId) {
        this.pingId = pingId;
    }

    public Integer getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(Integer withdrawalId) {
        this.withdrawalId = withdrawalId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Double getRealMoey() {
        return realMoey;
    }

    public void setRealMoey(Double realMoey) {
        this.realMoey = realMoey;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getIderName() {
        return iderName;
    }

    public void setIderName(String iderName) {
        this.iderName = iderName == null ? null : iderName.trim();
    }
}