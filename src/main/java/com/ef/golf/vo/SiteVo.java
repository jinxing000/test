package com.ef.golf.vo;

/**
 * for efgolf
 * Created by Bart on 2017/9/25.
 * Date: 2017/9/25 16:53
 * 球场信息
 */

public class SiteVo {

    private String siteID;//球场id

    private String siteModel;//球场模式

    private String minPrice;//球场价格

    private String siteParam;//球场数据

    private String siteName;//球场名字

    private String picUrl;//球场缩略图url

    private String distance;//距离

    private String siteAddr;//球场地址

    private Double score;//综合评分

    private int  commentNum;//评论数

    private int  praiseNum;//点赞数


    public Double getScore() {
        if(score==null){
            score=0.0;
        }
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getSiteAddr() {
        return siteAddr;
    }

    public void setSiteAddr(String siteAddr) {
        this.siteAddr = siteAddr;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getSiteModel() {
        return siteModel;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
