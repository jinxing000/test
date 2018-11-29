package com.ef.golf.pojo;

public class SpecValues {
    private Integer specValueId;

    private Integer specId;

    private String specValue;

    private String specImage;

    private Integer specOrder;

    private Integer specType;

    private Integer inherentOrAdd;

    public Integer getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue == null ? null : specValue.trim();
    }

    public String getSpecImage() {
        return specImage;
    }

    public void setSpecImage(String specImage) {
        this.specImage = specImage == null ? null : specImage.trim();
    }

    public Integer getSpecOrder() {
        return specOrder;
    }

    public void setSpecOrder(Integer specOrder) {
        this.specOrder = specOrder;
    }

    public Integer getSpecType() {
        return specType;
    }

    public void setSpecType(Integer specType) {
        this.specType = specType;
    }

    public Integer getInherentOrAdd() {
        return inherentOrAdd;
    }

    public void setInherentOrAdd(Integer inherentOrAdd) {
        this.inherentOrAdd = inherentOrAdd;
    }
}