package com.ef.golf.vo;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/13.
 */
public class JiaoYiHuizongVo {
    private Long id;/** 记录id */
    private Integer userId;/** 用户id */
    private String type;/** 交易类型(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送  ) */
   private String typeText;//交易类型文本
    private String createTime;/** 交易时间 */
    private Integer outIn;/** 账进出(1 进 2 出) */
    private Double money;/** 金额 */
    private String moneyText;//金额文本


    public JiaoYiHuizongVo() {
    }

    public JiaoYiHuizongVo(Long id, Integer userId, String type, String typeText, String createTime, Integer outIn, Double money, String moneyText) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.typeText = typeText;
        this.createTime = createTime;
        this.outIn = outIn;
        this.money = money;
        this.moneyText = moneyText;
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

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public String getMoneyText() {
        return moneyText;
    }

    public void setMoneyText(String moneyText) {
        this.moneyText = moneyText;
    }
}
