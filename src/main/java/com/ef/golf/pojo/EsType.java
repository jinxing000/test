package com.ef.golf.pojo;

public class EsType {
    private Integer typeId;

    private String name;

    private Integer protect;

    private String protectRate;

    private String hasCod;

    private String minPrice;

    private Integer corpId;

    private Integer ordernum;

    private Integer disabled;

    private Integer isSame;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProtect() {
        return protect;
    }

    public void setProtect(Integer protect) {
        this.protect = protect;
    }

    public String getProtectRate() {
        return protectRate;
    }

    public void setProtectRate(String protectRate) {
        this.protectRate = protectRate == null ? null : protectRate.trim();
    }

    public String getHasCod() {
        return hasCod;
    }

    public void setHasCod(String hasCod) {
        this.hasCod = hasCod == null ? null : hasCod.trim();
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice == null ? null : minPrice.trim();
    }

    public Integer getCorpId() {
        return corpId;
    }

    public void setCorpId(Integer corpId) {
        this.corpId = corpId;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getIsSame() {
        return isSame;
    }

    public void setIsSame(Integer isSame) {
        this.isSame = isSame;
    }
}