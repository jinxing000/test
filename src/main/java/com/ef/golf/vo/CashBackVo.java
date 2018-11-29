package com.ef.golf.vo;

/**
 * Created by Administrator on 2018/6/17.
 * 充值赠送比例Vo
 *   select ec.minnum,ec.maxnum,ec.maxnum-ec.minnum areaDistance,ec.proportion/100  rate from ef_cashback ec
 order by ec.minnum

 *
 *
 */
public class CashBackVo {
    private double minnum;//区段的最小值
    private double maxnum;// 最大值
    private double areaDistance;// 区间额
    private int rate;//比例

    public double getMinnum() {
        return minnum;
    }

    public void setMinnum(double minnum) {
        this.minnum = minnum;
    }

    public double getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(double maxnum) {
        this.maxnum = maxnum;
    }

    public double getAreaDistance() {
        return areaDistance;
    }

    public void setAreaDistance(double areaDistance) {
        this.areaDistance = areaDistance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

}
