package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/5/13 10:19
 */
public class JiaoYiHuizong {

    private Long id;/** 记录id */
    private Integer userId;/** 用户id */
    private String type;/** 交易类型(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送  ) */
    private Date createTime;/** 交易时间 */
    private Integer outIn;/** 账进出(1 进 2 出) */
    private Double money;/** 金额 */
    private String orderNo;/** 单号 */
    private String createUser;/** 操作人 */
    private String channel;/** 支付方式 */

    public JiaoYiHuizong() {
    }

    public JiaoYiHuizong(Long id, Integer userId, String type, Date createTime, Integer outIn, Double money, String orderNo,String createUser,String channel) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.createTime = createTime;
        this.outIn = outIn;
        this.money = money;
        this.orderNo = orderNo;
        this.createUser = createUser;
        this.channel = channel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOutIn() {
        return outIn;
    }

    public void setOutIn(Integer outIn) {
        this.outIn = outIn;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
