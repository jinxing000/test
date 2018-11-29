package com.ef.golf.pojo;

import java.math.BigDecimal;

public class LvPrice {
    private Integer id;

    private Integer productid;

    private Integer goodsid;

    private Integer lvid;

    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getLvid() {
        return lvid;
    }

    public void setLvid(Integer lvid) {
        this.lvid = lvid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}