package com.ef.golf.vo;

import java.math.BigDecimal;

/**
 * 热卖商品
 */
public class GoodsHotVo {

    private Integer goods_id;//id
    private Integer activity_id;//活动id
    private String name;//商品名
    private BigDecimal enable_store;//可用库存
    private BigDecimal price;//商品价格
    private BigDecimal mktprice;
    private String thumbnail;//缩略图
    private  Long start_time;//开始时间
    private  Long end_time;//结束时间
    private Integer state;//活动状态  根据时间比对出来的 0 进行中  1 结束

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(BigDecimal enable_store) {
        this.enable_store = enable_store;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getMktprice() {
        return mktprice;
    }

    public void setMktprice(BigDecimal mktprice) {
        this.mktprice = mktprice;
    }
}
