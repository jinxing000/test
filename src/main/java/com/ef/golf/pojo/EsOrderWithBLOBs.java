package com.ef.golf.pojo;

public class EsOrderWithBLOBs extends EsOrder {
    private String goods;

    private String remark;

    private String itemsJson;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getItemsJson() {
        return itemsJson;
    }

    public void setItemsJson(String itemsJson) {
        this.itemsJson = itemsJson == null ? null : itemsJson.trim();
    }
}