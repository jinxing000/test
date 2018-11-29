package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * create by xzw
 * 2017年12月20日15:14:11
 * 圈子动态  vo
 */
public class DynamicVo /*extends Page*/ implements Serializable {


    private Integer userId;//用户id
    private String userName;//用户名
    private String userType;//用户类型
    private String headPortraitUrl;//头像
    private String content;//内容
    private Integer dynamicId;//动态Id
    private String imgUrl;//图片url
    private String videoUrl;//视频url

    private Integer fabulousNum;//点赞数

    private Integer forwardNum;//评论数
    private Integer giftNum;//礼物数
    private Integer shareNum;//分享数
    private Date createTime;//创建时间
    private String cityName;//城市
    private String lat;//纬度
    private String lng;//经度
    private String isGood;//是否精选
    private String distance;//距离
    private String mediaSize;//图片宽高
    private String suoluetu;//视频缩略图

    private List<DynamicHopeVo> hopes;//动态一起显示的愿望列表
    private String dynamicImgs[];//动态的图片
    private List<QuanziUserVo> dynamicPraises;//动态的点赞
    private List<QuanziCommentVo> dynamicComments;//动态的评论列表
    private List<QuanziUserVo> dynamicShares;//分享列表
    private List<QuanziGiftVo> dynamicGift;//礼物列表

    private Boolean hasAttend;//是否点过赞 true 是 false 否

    public Boolean getHasAttend() {
        return hasAttend;
    }

    public void setHasAttend(Boolean hasAttend) {
        this.hasAttend = hasAttend;
    }

    public DynamicVo(Integer userId, String userName, String userType, String headPortraitUrl, String content, Integer dynamicId, String imgUrl, String videoUrl, Integer fabulousNum, Integer forwardNum, Integer giftNum, Integer shareNum, Date createTime, String cityName, String lat, String lng, String isGood, String distance, String mediaSize, List<DynamicHopeVo> hopes, String[] dynamicImgs) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.headPortraitUrl = headPortraitUrl;
        this.content = content;
        this.dynamicId = dynamicId;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.fabulousNum = fabulousNum;
        this.forwardNum = forwardNum;
        this.giftNum = giftNum;
        this.shareNum = shareNum;
        this.createTime = createTime;
        this.cityName = cityName;
        this.lat = lat;
        this.lng = lng;
        this.isGood = isGood;
        this.distance = distance;
        this.hopes = hopes;
        this.dynamicImgs = dynamicImgs;
        this.mediaSize = mediaSize;
    }

    public DynamicVo(){}

    public String getSuoluetu() {
        return suoluetu;
    }

    public void setSuoluetu(String suoluetu) {
        this.suoluetu = suoluetu;
    }

    public String getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(String mediaSize) {
        this.mediaSize = mediaSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(Integer fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public Integer getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
        this.giftNum = giftNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
    }

    public String getDistance() {
        /** if(distance!=null){
            distance = (Double.valueOf(distance)/1000d)+"";
        }*/
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<DynamicHopeVo> getHopes() {
        return hopes;
    }

    public void setHopes(List<DynamicHopeVo> hopes) {
        this.hopes = hopes;
    }

    public String[] getDynamicImgs() {
        return dynamicImgs;
    }

    public void setDynamicImgs(String[] dynamicImgs) {
        this.dynamicImgs = dynamicImgs;
    }

    public List<QuanziUserVo> getDynamicPraises() {
        return dynamicPraises;
    }

    public void setDynamicPraises(List<QuanziUserVo> dynamicPraises) {
        this.dynamicPraises = dynamicPraises;
    }

    public List<QuanziCommentVo> getDynamicComments() {
        return dynamicComments;
    }

    public void setDynamicComments(List<QuanziCommentVo> dynamicComments) {
        this.dynamicComments = dynamicComments;
    }

    public List<QuanziUserVo> getDynamicShares() {
        return dynamicShares;
    }

    public void setDynamicShares(List<QuanziUserVo> dynamicShares) {
        this.dynamicShares = dynamicShares;
    }

    public List<QuanziGiftVo> getDynamicGift() {
        return dynamicGift;
    }

    public void setDynamicGift(List<QuanziGiftVo> dynamicGift) {
        this.dynamicGift = dynamicGift;
    }
}
