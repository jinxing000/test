package com.ef.golf.vo;


import java.util.Date;

/**
 * create by xzw
 * 2018年1月20日12:02:43
 * 保存愿望的详细信息
 */
public class QuanziHopeDetailVo {

    private String userId;//发布愿望者的id
    private String nickName;//昵称
    private String headPortraitUrl;//头像url
    private String gender;//性别
    private String hopeContent;//愿望内容
    private String hopeId;//愿望id
    private String hopeType;//愿望类型
    private String hopeState;//愿望状态
    private String imgUrl;//愿望图片url;
    private String hopeName;//愿望名称
    private Date createTime;//创建时间
    private String reserved4;//备用字段4(领取时间)
    private Date realizeTime;//愿望实现时间
    private String getStauts;//愿望状态
    private Double hopeMoney;//实现愿望所需金额
    private Double everyHopeMoney;//每一个支持的金额
    private Double hopeRealMoney;//当前已经实现的金额
    private Double scale;//当前实现比例
    private String playTime;
    private String prductRule;

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHopeContent() {
        return hopeContent;
    }

    public void setHopeContent(String hopeContent) {
        this.hopeContent = hopeContent;
    }

    public String getHopeId() {
        return hopeId;
    }

    public void setHopeId(String hopeId) {
        this.hopeId = hopeId;
    }

    public String getHopeType() {
        return hopeType;
    }

    public void setHopeType(String hopeType) {
        this.hopeType = hopeType;
    }

    public String getHopeState() {
        return hopeState;
    }

    public void setHopeState(String hopeState) {
        this.hopeState = hopeState;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHopeName() {
        return hopeName;
    }

    public void setHopeName(String hopeName) {
        this.hopeName = hopeName;
    }

    public String getReserved4() {
        return reserved4;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getPrductRule() {
        return prductRule;
    }

    public void setPrductRule(String prductRule) {
        this.prductRule = prductRule;
    }
}
