package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/5/12 9:32
 */
public class SystemTransfer {

    private Integer id;
    private Long amount;//金额(分)
    private Date timeTransferred;//交易完成时间
    private String status;//付款状态
    private String recipient;//收款人ID
    private String description;//充值备注信息
    private String transactionNo;//第三方流水号
    private String orderNo;//本地订单号
    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getTimeTransferred() {
        return timeTransferred;
    }

    public void setTimeTransferred(Date timeTransferred) {
        this.timeTransferred = timeTransferred;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SystemTransfer(Integer id, Long amount, Date timeTransferred, String status, String recipient, String description, String transactionNo, String orderNo, Date createTime) {
        this.id = id;
        this.amount = amount;
        this.timeTransferred = timeTransferred;
        this.status = status;
        this.recipient = recipient;
        this.description = description;
        this.transactionNo = transactionNo;
        this.orderNo = orderNo;
        this.createTime = createTime;
    }

    public SystemTransfer() {
    }
}
