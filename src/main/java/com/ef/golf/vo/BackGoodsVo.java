package com.ef.golf.vo;

import java.math.BigDecimal;

public class BackGoodsVo {
    private String goods_name;
    private String specs;//规格
    private Integer return_num;//退货数量
    private BigDecimal price;//退货价格
    private String thumbnail;//缩略图

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getReturn_num() {
        return return_num;
    }

    public void setReturn_num(Integer return_num) {
        this.return_num = return_num;
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
}
