package com.ef.golf.vo;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/5/7 14:35
 * 商品收藏个人中心
 */
public class CollectGoodsListVo {
    private Integer collectId;//收藏id
    private Integer goods_id;//商品id
    private String name;//商品名称
    private Double price;//价格
    private Integer enableStore;//可用库存
    private String thumbnail;//图片

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getGoods_id() {
        return goods_id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getEnableStore() {
        return enableStore;
    }

    public void setEnableStore(Integer enableStore) {
        this.enableStore = enableStore;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
