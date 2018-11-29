package com.ef.golf.pojo;

public class Activity {
    private Integer activityId;

    private String activityName;

    private Long startTime;

    private Long endTime;

    private Short rangeType;

    private Short activityType;

    private Short disabled;

    private String description;

    private String img;

    private String color;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Short getRangeType() {
        return rangeType;
    }

    public void setRangeType(Short rangeType) {
        this.rangeType = rangeType;
    }

    public Short getActivityType() {
        return activityType;
    }

    public void setActivityType(Short activityType) {
        this.activityType = activityType;
    }

    public Short getDisabled() {
        return disabled;
    }

    public void setDisabled(Short disabled) {
        this.disabled = disabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}