package com.ef.golf.vo;

import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.pojo.*;

import java.util.Date;

/**
 * for golf
 * Created by Administrator
 * Date: 2018/8/31
 */
public class OrderCommonVo{

    private Order order;
    private Qiutong_order qiutongOrder;
    private Coach_order coachOrder;
    private Course_order courseOrder;
    private SiteOrderDatailVo siteOrder;

    public OrderCommonVo() {
    }

    public OrderCommonVo(Order order, Qiutong_order qiutongOrder, Coach_order coachOrder, Course_order courseOrder, SiteOrderDatailVo siteOrder) {
        this.order = order;
        this.qiutongOrder = qiutongOrder;
        this.coachOrder = coachOrder;
        this.courseOrder = courseOrder;
        this.siteOrder = siteOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Qiutong_order getQiutongOrder() {
        return qiutongOrder;
    }

    public void setQiutongOrder(Qiutong_order qiutongOrder) {
        this.qiutongOrder = qiutongOrder;
    }

    public Coach_order getCoachOrder() {
        return coachOrder;
    }

    public void setCoachOrder(Coach_order coachOrder) {
        this.coachOrder = coachOrder;
    }

    public Course_order getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(Course_order courseOrder) {
        this.courseOrder = courseOrder;
    }

    public SiteOrderDatailVo getSiteOrder() {
        return siteOrder;
    }

    public void setSiteOrder(SiteOrderDatailVo siteOrder) {
        this.siteOrder = siteOrder;
    }
}
