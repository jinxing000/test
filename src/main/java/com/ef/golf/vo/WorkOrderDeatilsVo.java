package com.ef.golf.vo;

import java.util.Date;

/**
 * 订单详情
 */
public class WorkOrderDeatilsVo {

    private String orderNo;//订单编号
    private Integer orderId;//订单id
    private Double coachOrderMoney;//教练订单价格
    private String orderType;//订单类型
    private Date createTime;//创建时间
    private String orderState;//订单状态
    private String coachPlayDate;//打球日期
    private String coachPlayTime;//打球时间
    private String coachPlayName;//教练名字
    private String coachContactsPhone;//教练联系方式
    private Integer coachQiuHuiId;//球会id
    private String coachQiuHuiName;//球会名字
    private String coachRemark;//备注
    private String qiuTongPlayDate;//球童打球日期
    private String qiuTongPlayTime;//球童打球时间
    private String qiuTongPlayName;//球童名字
    private String qiuTongContactsPhone;//球童电话
    private Integer qiuTongQiuHuiId;//球童所属球会id
    private String qiuTongQiuHuiName;//球童所属球会名
    private String qiuTongRemark;//球童备注
    private String courseCoachId;//课程
    private String courseCoachName;
    private Integer siteId;//球场id
    private String siteName;//球场名字
    private String courseType;//课程类型
    private String courseTime;//上课时间
    private String courseDate;//上课日期
    private String courseNum;//课程次数
    private String courseName;//课程名称
    private String reserveName;//课程预约人
    private String courseContactsPhone;//课程手机号
    private String courseRemark;//课程预约备注

    public String getCourseCoachName() {
        return courseCoachName;
    }

    public void setCourseCoachName(String courseCoachName) {
        this.courseCoachName = courseCoachName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getCoachOrderMoney() {
        return coachOrderMoney;
    }

    public void setCoachOrderMoney(Double coachOrderMoney) {
        this.coachOrderMoney = coachOrderMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCoachPlayDate() {
        return coachPlayDate;
    }

    public void setCoachPlayDate(String coachPlayDate) {
        this.coachPlayDate = coachPlayDate;
    }

    public String getCoachPlayTime() {
        return coachPlayTime;
    }

    public void setCoachPlayTime(String coachPlayTime) {
        this.coachPlayTime = coachPlayTime;
    }

    public String getCoachPlayName() {
        return coachPlayName;
    }

    public void setCoachPlayName(String coachPlayName) {
        this.coachPlayName = coachPlayName;
    }

    public String getCoachContactsPhone() {
        return coachContactsPhone;
    }

    public void setCoachContactsPhone(String coachContactsPhone) {
        this.coachContactsPhone = coachContactsPhone;
    }

    public Integer getCoachQiuHuiId() {
        return coachQiuHuiId;
    }

    public void setCoachQiuHuiId(Integer coachQiuHuiId) {
        this.coachQiuHuiId = coachQiuHuiId;
    }

    public String getCoachQiuHuiName() {
        return coachQiuHuiName;
    }

    public void setCoachQiuHuiName(String coachQiuHuiName) {
        this.coachQiuHuiName = coachQiuHuiName;
    }

    public String getCoachRemark() {
        return coachRemark;
    }

    public void setCoachRemark(String coachRemark) {
        this.coachRemark = coachRemark;
    }

    public String getQiuTongPlayDate() {
        return qiuTongPlayDate;
    }

    public void setQiuTongPlayDate(String qiuTongPlayDate) {
        this.qiuTongPlayDate = qiuTongPlayDate;
    }

    public String getQiuTongPlayTime() {
        return qiuTongPlayTime;
    }

    public void setQiuTongPlayTime(String qiuTongPlayTime) {
        this.qiuTongPlayTime = qiuTongPlayTime;
    }

    public String getQiuTongPlayName() {
        return qiuTongPlayName;
    }

    public void setQiuTongPlayName(String qiuTongPlayName) {
        this.qiuTongPlayName = qiuTongPlayName;
    }

    public String getQiuTongContactsPhone() {
        return qiuTongContactsPhone;
    }

    public void setQiuTongContactsPhone(String qiuTongContactsPhone) {
        this.qiuTongContactsPhone = qiuTongContactsPhone;
    }

    public Integer getQiuTongQiuHuiId() {
        return qiuTongQiuHuiId;
    }

    public void setQiuTongQiuHuiId(Integer qiuTongQiuHuiId) {
        this.qiuTongQiuHuiId = qiuTongQiuHuiId;
    }

    public String getQiuTongQiuHuiName() {
        return qiuTongQiuHuiName;
    }

    public void setQiuTongQiuHuiName(String qiuTongQiuHuiName) {
        this.qiuTongQiuHuiName = qiuTongQiuHuiName;
    }

    public String getQiuTongRemark() {
        return qiuTongRemark;
    }

    public void setQiuTongRemark(String qiuTongRemark) {
        this.qiuTongRemark = qiuTongRemark;
    }

    public String getCourseCoachId() {
        return courseCoachId;
    }

    public void setCourseCoachId(String courseCoachId) {
        this.courseCoachId = courseCoachId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
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

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getCourseContactsPhone() {
        return courseContactsPhone;
    }

    public void setCourseContactsPhone(String courseContactsPhone) {
        this.courseContactsPhone = courseContactsPhone;
    }

    public String getCourseRemark() {
        return courseRemark;
    }

    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }
}
