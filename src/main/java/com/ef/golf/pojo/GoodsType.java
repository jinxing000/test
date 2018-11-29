package com.ef.golf.pojo;

public class GoodsType {
    private Integer typeId;

    private String name;

    private Integer disabled;

    private Integer isPhysical;

    private Integer haveProp;

    private Integer haveParm;

    private Integer joinBrand;

    private Integer haveField;

    private Integer joinSpec;

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

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getIsPhysical() {
        return isPhysical;
    }

    public void setIsPhysical(Integer isPhysical) {
        this.isPhysical = isPhysical;
    }

    public Integer getHaveProp() {
        return haveProp;
    }

    public void setHaveProp(Integer haveProp) {
        this.haveProp = haveProp;
    }

    public Integer getHaveParm() {
        return haveParm;
    }

    public void setHaveParm(Integer haveParm) {
        this.haveParm = haveParm;
    }

    public Integer getJoinBrand() {
        return joinBrand;
    }

    public void setJoinBrand(Integer joinBrand) {
        this.joinBrand = joinBrand;
    }

    public Integer getHaveField() {
        return haveField;
    }

    public void setHaveField(Integer haveField) {
        this.haveField = haveField;
    }

    public Integer getJoinSpec() {
        return joinSpec;
    }

    public void setJoinSpec(Integer joinSpec) {
        this.joinSpec = joinSpec;
    }
}