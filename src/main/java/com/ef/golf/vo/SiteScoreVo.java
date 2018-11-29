package com.ef.golf.vo;

import java.io.Serializable;

/**
 * create by xzw
 * 2017年10月16日14:17:30
 * 评分实体类
 */
public class SiteScoreVo implements Serializable {

    private Integer attr_id;//评分属性id
    private String attr_nam;//评分名
    private String attr_ascription;//评分归属（1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程）


    private Integer product_score_id;//被评论对象的属性的id
    private Integer product_id;//被评论对象的id
    private String product_type;//被评论对象的类型
    private String score;//分值

    public SiteScoreVo(){}

    public SiteScoreVo(Integer attr_id, String attr_nam, String attr_ascription, Integer product_score_id, Integer product_id, String product_type, String score) {
        this.attr_id = attr_id;
        this.attr_nam = attr_nam;
        this.attr_ascription = attr_ascription;
        this.product_score_id = product_score_id;
        this.product_id = product_id;
        this.product_type = product_type;
        this.score = score;
    }

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_nam() {
        return attr_nam;
    }

    public void setAttr_nam(String attr_nam) {
        this.attr_nam = attr_nam;
    }

    public String getAttr_ascription() {
        return attr_ascription;
    }

    public void setAttr_ascription(String attr_ascription) {
        this.attr_ascription = attr_ascription;
    }

    public Integer getProduct_score_id() {
        return product_score_id;
    }

    public void setProduct_score_id(Integer product_score_id) {
        this.product_score_id = product_score_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
