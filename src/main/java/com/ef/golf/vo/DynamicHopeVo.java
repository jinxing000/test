package com.ef.golf.vo;

import com.ef.golf.pojo.Dynamic;
import com.ef.golf.util.EncryptUtil;

/**
 * create by xzw
 * 2017年12月22日11:38:31
 * 保存圈子里愿望的  vo
 */

public class DynamicHopeVo {
    private Integer hopeId;//愿望id
    private String hopeType;//愿望类型
    private Integer userId;//发布人id
    private String prductRule;//实现规则
    private String hopeState;//愿望状态
    private String imgUrl;//愿望图片url
    private String productName;//球场或者商品名字
    private String hopeName;//愿望名字
    private String scale;

    public DynamicHopeVo(){}

    public DynamicHopeVo(Integer hopeId, String hopeType, Integer userId, String prductRule, String hopeState, String imgUrl, String productName, String hopeName, String scale) {
        this.hopeId = hopeId;
        this.hopeType = hopeType;
        this.userId = userId;
        this.prductRule = prductRule;
        this.hopeState = hopeState;
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.hopeName = hopeName;
        this.scale = scale;
    }

    public Integer getHopeId() {
        return hopeId;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public String getHopeType() {
        return hopeType;
    }

    public void setHopeType(String hopeType) {
        this.hopeType = hopeType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPrductRule() {
        return prductRule;
    }

    public void setPrductRule(String prductRule) {
        this.prductRule = prductRule;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHopeName() {
        return hopeName;
    }

    public void setHopeName(String hopeName) {
        this.hopeName = hopeName;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
