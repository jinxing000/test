package com.ef.golf.pojo;

import java.math.BigDecimal;

public class GoodsMarketVo {

    private Integer goods_id;
    private BigDecimal price;
    private  Integer product_id;
    private String name;
    private  String pname;
    private  Integer store;
    private Integer enable_store;
    private  BigDecimal mktprice;

    private String thumbnail;
    private String specs;

    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(Integer enable_store) {
        this.enable_store = enable_store;
    }

    public BigDecimal getMktprice() {
        return mktprice;
    }

    public void setMktprice(BigDecimal mktprice) {
        this.mktprice = mktprice;
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
}
