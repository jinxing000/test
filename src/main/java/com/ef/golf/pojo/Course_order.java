package com.ef.golf.pojo;


import java.util.Date;

public class Course_order {
     private Integer courseOrderId;
     private Long orderId;
     private Integer coachId;
     private Integer courseId;
     private String  coachName;
     private Integer courseNum;
     private String  courseName;
     private Integer siteId;
     private String siteName;
     private String reserveName;
     private String contactsPhone;
     private String remark;
     private Integer tickId;
     private String courseTime;
     private String courseDate;
     private Date createTime;
     private Date modifyTime;
     private String createUser;
     private String modifyUser;

    public Integer getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(Integer courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTickId() {
        return tickId;
    }

    public void setTickId(Integer tickId) {
        this.tickId = tickId;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Course_order() {
    }

    public Course_order(Integer courseOrderId, Long orderId, Integer coachId, Integer courseId, String coachName, Integer courseNum, String courseName, Integer siteId, String siteName, String reserveName, String contactsPhone, String remark, Integer tickId, String courseTime, String courseDate, Date createTime, Date modifyTime, String createUser, String modifyUser) {
        this.courseOrderId = courseOrderId;
        this.orderId = orderId;
        this.coachId = coachId;
        this.courseId = courseId;
        this.coachName = coachName;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.siteId = siteId;
        this.siteName = siteName;
        this.reserveName = reserveName;
        this.contactsPhone = contactsPhone;
        this.remark = remark;
        this.tickId = tickId;
        this.courseTime = courseTime;
        this.courseDate = courseDate;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }
}