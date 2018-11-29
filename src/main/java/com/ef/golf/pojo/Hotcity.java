package com.ef.golf.pojo;

public class Hotcity {
    private Integer hotcityId;

    private String hotcity;

    public Integer getHotcityId() {
        return hotcityId;
    }

    public void setHotcityId(Integer hotcityId) {
        this.hotcityId = hotcityId;
    }

    public String getHotcity() {
        return hotcity;
    }

    public void setHotcity(String hotcity) {
        this.hotcity = hotcity == null ? null : hotcity.trim();
    }
}