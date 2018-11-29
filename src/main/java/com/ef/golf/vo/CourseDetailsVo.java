package com.ef.golf.vo;

import org.apache.commons.lang.StringUtils;

public class CourseDetailsVo {

    private Integer coachId;//教练ID
    private String coachName;//教练真实姓名
    private String coachHead;//教练头像
    private Integer siteId; //球场id
    private String siteName;//隶属球场
    private Integer id;//课程id
    private String courseName;//课程名
    private Double price; //价格
    private String imgUrl;//课程url
    private String courseNum;//课程次数
    private String courseType;//根据次数产生  单次  套次
    private String introduce;//课程介绍
    private String[] imgs;//封装课程图片


    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String[] getImgs() {
        if (StringUtils.isNotBlank(imgUrl)){
            imgs = imgUrl.split(",");
        }
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoachHead() {
        return coachHead;
    }

    public void setCoachHead(String coachHead) {
        this.coachHead = coachHead;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CourseDetailsVo() {
    }

    // private Integer coachId;//教练ID
    //private String coachName;//教练真实姓名

    public CourseDetailsVo(Integer coachId, String coachName, Integer siteId, String siteName, Integer id, String courseName, Double price,String imgUrl, String courseNum, String courseType, String introduce, String[] imgs) {
        this.coachId = coachId;
        this.coachName = coachName;
        this.siteId = siteId;
        this.siteName = siteName;
        this.id = id;
        this.courseName = courseName;
        this.price=price;
        this.imgUrl = imgUrl;
        this.courseNum = courseNum;
        this.courseType = courseType;
        this.introduce = introduce;
        this.imgs = imgs;
    }
}
