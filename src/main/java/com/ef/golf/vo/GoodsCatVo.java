package com.ef.golf.vo;

import java.math.BigDecimal;

/**
 *商品类别
 */
public class GoodsCatVo {

   private  Integer goods_id;//商品id
   private  String name;//品名
   private BigDecimal price;//价格
    private Integer enable_store;//可用库存  Android  应急  后面需要统一成enableStore驼峰
    private Integer enableStore;//可用库存   iOS
    private String tag_name;//标签名
    private  String catname;//分类名
    private  Integer cat_id;//分类id
    private String thumbnail;//缩略图
    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public Integer getEnableStore() {
        return enableStore;
    }

    public void setEnableStore(Integer enableStore) {
        this.enableStore = enableStore;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(Integer enable_store) {
        this.enable_store = enable_store;
    }
}

