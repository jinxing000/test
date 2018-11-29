package com.ef.golf.pojo;

import java.util.Date;

public class CourseSign {

    private Long signId;//签到记录ID
    private Integer courseId;//课程ID
    private Integer stuId;//学员ID
    private Integer caochId;//教练ID
    private String coachSignStauts;//教练签到状态 教练签到状态(0 未签到 1 已签到 2 不可签到)
    private Integer signNum;//签到次数==课程次数
    private String stuSignStauts;//学员签到状态 学员签到状态(0 未签到 1 已签到 2 不可签到)
    private Date stuSignDate;//学员签到时间
    private Date coachSignDate;//教练签到时间
    private String coachSign;//教练签到状态
    private String stuSign;//教练签到状态
    private Integer orderId;//订单id

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCoachSign() {
        return coachSign;
    }

    public void setCoachSign(String coachSign) {
        this.coachSign = coachSign;
    }

    public String getStuSign() {
        return stuSign;
    }

    public void setStuSign(String stuSign) {
        this.stuSign = stuSign;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCaochId() {
        return caochId;
    }

    public void setCaochId(Integer caochId) {
        this.caochId = caochId;
    }

    public String getCoachSignStauts() {
        return coachSignStauts;
    }

    public void setCoachSignStauts(String coachSignStauts) {
        this.coachSignStauts = coachSignStauts;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public String getStuSignStauts() {
        return stuSignStauts;
    }

    public void setStuSignStauts(String stuSignStauts) {
        this.stuSignStauts = stuSignStauts;
    }

    public Date getStuSignDate() {
        return stuSignDate;
    }

    public void setStuSignDate(Date stuSignDate) {
        this.stuSignDate = stuSignDate;
    }

    public Date getCoachSignDate() {
        return coachSignDate;
    }

    public void setCoachSignDate(Date coachSignDate) {
        this.coachSignDate = coachSignDate;
    }
}
