package com.ef.golf.pojo;



public class OrderItem {
    private Integer itemId;

    private Integer orderId;

    private Integer goodsId;

    private Integer productId;

    private Integer catId;

    private Integer num;

    private Integer shipNum;

    private String sn;

    private String name;

    private Integer price;

    private Integer gainedpoint;

    private Integer state;

    private String changeGoodsName;

    private Integer changeGoodsId;

    private String unit;

    private Short goodsType;

    private Integer snapshotId;

    private String image;

    private String addon;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon == null ? null : addon.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getShipNum() {
        return shipNum;
    }

    public void setShipNum(Integer shipNum) {
        this.shipNum = shipNum;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGainedpoint() {
        return gainedpoint;
    }

    public void setGainedpoint(Integer gainedpoint) {
        this.gainedpoint = gainedpoint;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getChangeGoodsName() {
        return changeGoodsName;
    }

    public void setChangeGoodsName(String changeGoodsName) {
        this.changeGoodsName = changeGoodsName == null ? null : changeGoodsName.trim();
    }

    public Integer getChangeGoodsId() {
        return changeGoodsId;
    }

    public void setChangeGoodsId(Integer changeGoodsId) {
        this.changeGoodsId = changeGoodsId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Short getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Short goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }
}