package com.ef.golf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Hope{
    private Integer hopeId;//愿望ID
    private String uid;
    private Integer userId;//用户ID
    private String hopeState;//心愿状态（1未完成 2完成 3失效）
    private String hopeType;//愿望类型（1场地 2商品）
    private Integer productId;//产品id(球场,商品)
    private Double hopeMoney;//订单金额（总金额）
    private Double everyHopeMoney;//实现愿望每份金额
    private Double hopeRealMoney;//已实现金额
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;//开球日期
    private String beginTime;//开球时间
    private String prductRule;//商品规格（前端拼接好字符串传入后台
    private String hopeContent;//愿望内容
    private Date createTime;//创建时间
    private String reserved1;//愿望图片
    private String reserved2;//愿望名称
    private Date reserved3;//预留字段3(修改为失效日期)
    private Double scale;//比例
    private Date reserved4;//预留字段4(领取时间)
    private Date realizeTime;//实现时间
    private String getStauts;//领取状态(0,未领取 1，已领取 2，已过期)
    private String playTime;

    public String getUid() {
        return uid;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Date getRealizeTime() {
        return realizeTime;
    }

    public void setRealizeTime(Date realizeTime) {
        this.realizeTime = realizeTime;
    }

    public String getGetStauts() {
        return getStauts;
    }

    public void setGetStauts(String getStauts) {
        this.getStauts = getStauts;
    }

    public Integer getHopeId() {
        return hopeId;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHopeState() {
        return hopeState;
    }

    public void setHopeState(String hopeState) {
        this.hopeState = hopeState == null ? null : hopeState.trim();
    }

    public String getHopeType() {
        return hopeType;
    }

    public void setHopeType(String hopeType) {
        this.hopeType = hopeType == null ? null : hopeType.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getHopeMoney() {
        return hopeMoney;
    }

    public void setHopeMoney(Double hopeMoney) {
        this.hopeMoney = hopeMoney;
    }

    public Double getEveryHopeMoney() {
        return everyHopeMoney;
    }

    public void setEveryHopeMoney(Double everyHopeMoney) {
        this.everyHopeMoney = everyHopeMoney;
    }

    public Double getHopeRealMoney() {
        return hopeRealMoney;
    }

    public void setHopeRealMoney(Double hopeRealMoney) {
        this.hopeRealMoney = hopeRealMoney;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getPrductRule() {
        return prductRule;
    }

    public void setPrductRule(String prductRule) {
        this.prductRule = prductRule == null ? null : prductRule.trim();
    }

    public String getHopeContent() {
        return hopeContent;
    }

    public void setHopeContent(String hopeContent) {
        this.hopeContent = hopeContent == null ? null : hopeContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public Date getReserved3() {
        return reserved3;
    }

    public void setReserved3(Date reserved3) {
        this.reserved3 = reserved3;
    }

    public Date getReserved4() {
        return reserved4;
    }

    public void setReserved4(Date reserved4) {
        this.reserved4 = reserved4;
    }
}