package com.ef.golf.pojo;

public class Order_Meta {
    private Integer metaid;

    private Integer orderid;

    private String metaKey;

    private String metaValue;

    public Integer getMetaid() {
        return metaid;
    }

    public void setMetaid(Integer metaid) {
        this.metaid = metaid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey == null ? null : metaKey.trim();
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue == null ? null : metaValue.trim();
    }
}