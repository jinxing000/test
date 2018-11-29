package com.ef.golf.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/7.
 * 预先分配好的红包
 */
public class SmallRedPackage implements Serializable {
    private static final long serialVersionUID = -6926921365242575387L;
    //大红包的id
    private String bigRedPackageId;

    //小红包的项次
    private int xiangci;

    //接收者id
    private Integer receiverId;

    //接收时间
    private Date receiveTime;

    // 金额
    private int smallMoneyAmount;

    //分配标记  0:未分配  1:已分配
    private int hasQiang;

    //是否为手气最佳  0:非 1:最佳
    private int  best;

    public SmallRedPackage() {
    }

    public SmallRedPackage(String bigRedPackageId, int xiangci, Integer receiverId, Date receiveTime, int smallMoneyAmount, int hasQiang, int best) {
        this.bigRedPackageId = bigRedPackageId;
        this.xiangci = xiangci;
        this.receiverId = receiverId;
        this.receiveTime = receiveTime;
        this.smallMoneyAmount=smallMoneyAmount;
        this.hasQiang = hasQiang;
        this.best=best;
    }

    public String getBigRedPackageId() {
        return bigRedPackageId;
    }

    public void setBigRedPackageId(String bigRedPackageId) {
        this.bigRedPackageId = bigRedPackageId;
    }

    public int getXiangci() {
        return xiangci;
    }

    public void setXiangci(int xiangci) {
        this.xiangci = xiangci;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public int getSmallMoneyAmount() {
        return smallMoneyAmount;
    }

    public void setSmallMoneyAmount(int smallMoneyAmount) {
        this.smallMoneyAmount = smallMoneyAmount;
    }

    public int getHasQiang() {
        return hasQiang;
    }

    public void setHasQiang(int hasQiang) {
        this.hasQiang = hasQiang;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }
}
