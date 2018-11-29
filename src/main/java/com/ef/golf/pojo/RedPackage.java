package com.ef.golf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/7.
 */
public class RedPackage implements Serializable{

    //红包id
    private String id;//按一定规则生成
    //红包金额
    private Integer moneyAmount;
    //红包个数
    private  Integer count;
    //红包发送者
    private Integer userId;
    //红包发送时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date sendTime;
    //群id
    private String qunId;
    //祝福
    private String content;
    //是否有效
    private int state;
    //版本号
    private int version;

    public RedPackage() {
    }

    public RedPackage(String id, Integer moneyAmount, Integer count, Integer userId, Date sendTime, String qunId, String content, int state, int version) {
        this.id = id;
        this.moneyAmount = moneyAmount;
        this.count = count;
        this.userId = userId;
        this.sendTime = sendTime;
        this.qunId = qunId;
        this.content = content;
        this.state = state;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getQunId() {
        return qunId;
    }

    public void setQunId(String qunId) {
        this.qunId = qunId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
