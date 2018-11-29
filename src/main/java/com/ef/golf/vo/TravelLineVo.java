package com.ef.golf.vo;

import com.ef.golf.pojo.TravelLineDetailModule;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/11/14 10:30
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelLineVo {

    private Integer lineId;	// 线路id
    private String lineName; // 线路名称
    private String destination; // 线路目的地
    private String realPrice; //实际价格
    private String originalPrice; //原始价格
    private String picture;     // 单个图片
    private List<String> pictures;//图片列表
    private Integer sort;//专题内排序字段 前端不用
    private String city;//城市
    private String country;//国家
    private List<TravelLineDetailModule> lineDetailModules;

    private String sTime;
    private String xTime;
    private String phone;

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getxTime() {
        return xTime;
    }

    public void setxTime(String xTime) {
        this.xTime = xTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<TravelLineDetailModule> getLineDetailModules() {
        return lineDetailModules;
    }

    public void setLineDetailModules(List<TravelLineDetailModule> lineDetailModules) {
        this.lineDetailModules = lineDetailModules;
    }


}
