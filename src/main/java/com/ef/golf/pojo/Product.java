package com.ef.golf.pojo;



public class Product {
    private Integer productId;

    private Integer goodsId;

    private String name;

    private String sn;

    private Integer store;

    private Integer enableStore;

    private Integer price;

    private Integer cost;

    private Integer weight;

    private Integer isPack;

    private String specs;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getEnableStore() {
        return enableStore;
    }

    public void setEnableStore(Integer enableStore) {
        this.enableStore = enableStore;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsPack() {
        return isPack;
    }

    public void setIsPack(Integer isPack) {
        this.isPack = isPack;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs == null ? null : specs.trim();
    }
}