package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;

/**
 * 课程列表Vo
 */
public class CoachCourseVo extends Page{
    //课程id
    private Integer id;
    //课程名字
    private String courseName;
    //课程图片
    private String imgUrl;
    //教练名字
    private String coachName;
    //价格
    private Double price;
    //单套次
    private  String  courseType;

    private String recommend;


    public CoachCourseVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
