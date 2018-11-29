package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;

import java.io.Serializable;
import java.util.Date;

/**
 *球场收藏数据
 */
public class SiteCollectsVo{

    private Integer siteId;//球场id

    private Date createTime;//创建时间

    private String siteModel;//球场模式

    private String collectType;//被收藏的对象对应的类型

    private String minPrice;//球场价格

    private String siteParam;//球场数据

    private String siteName;//球场名称

    private String picUrl;//球场图片路径url

    private String siteAddress;//球场地址

    private Double score;

    private String distance;//距离

    private String siteAddr;//球场地址

    private int  commentNum;//评论数

    private int  praiseNum;//点赞数


    public SiteCollectsVo(){}

    public SiteCollectsVo(Integer siteId, Date createTime, String siteModel, String collectType, String minPrice, String siteParam, String siteName, String picUrl, String siteAddress, Double score, String distance, String siteAddr, int commentNum, int praiseNum) {
        this.siteId = siteId;
        this.createTime = createTime;
        this.siteModel = siteModel;
        this.collectType = collectType;
        this.minPrice = minPrice;
        this.siteParam = siteParam;
        this.siteName = siteName;
        this.picUrl = picUrl;
        this.siteAddress = siteAddress;
        this.score = score;
        this.distance = distance;
        this.siteAddr = siteAddr;
        this.commentNum = commentNum;
        this.praiseNum = praiseNum;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSiteModel() {
        return siteModel;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public void setSiteModel(String siteModel) {
        this.siteModel = siteModel;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getSiteParam() {
        return siteParam;
    }

    public void setSiteParam(String siteParam) {
        this.siteParam = siteParam;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDistance() {
        if(distance!=null){
            distance = (Double.valueOf(distance)/1000)+"";
        }
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSiteAddr() {
        return siteAddr;
    }

    public void setSiteAddr(String siteAddr) {
        this.siteAddr = siteAddr;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }
}
