package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.*;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 教练工单查询
 * 1.待支付 2.球位确认中 3.确认成功 4.待评价 5.已完成 6.取消支付
 * 7.已退订 8.待回应 9.成功预约 10.已取消 11.未接受
 * 12.待发货 13.已发货  14.申请退货中 15.待寄件 16.退货中 17.已退货
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserOrderAction extends AbstractService {

    @Resource
    private SiteOrderService siteOrderService;
    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private CoachOrderService coachOrderService;
    @Resource
    private CourseService courseService;
    @Resource
    private EsOrderService esOrderService;

    //@NotNull(message = "用户ID必须")
    private Integer userId; //jdk1.8.0_191
    private String orderState;
    private String orderType;
    /**
     * 2.场地 3.教练 4.球童 8.商城 0.课程
     */
    private int pageNo = 1;
    private int pageSize = 5;

    @Override
    public Object doService() throws Exception {
        PageBean pageBean = null;
        switch (orderType) {
            case "2":/** 场地 */
                pageBean = siteOrderService.getSiteOrderList(userId.toString(), orderState, pageNo, pageSize);
                if (pageBean.getResultList().size() > 0) {
                    break;
                }
            case "4":/** 球童 */
                pageBean = qiutongOrderService.getQiuTongOrderList(userId.toString(), orderState, pageNo, pageSize);
                if (pageBean.getResultList().size() > 0) {
                    break;
                }
            case "3":/** 教练 */
                pageBean = coachOrderService.getCoachOrderList(userId.toString(), orderState, pageNo, pageSize);
                if (pageBean.getResultList().size() > 0) {
                    break;
                }
            case "0":/** 课程 */
                pageBean = courseService.getCourseOrderList(userId.toString(), orderState, pageNo, pageSize);
                if (pageBean.getResultList().size() > 0) {
                    break;
                }
        }
        return pageBean;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setEsOrderService(EsOrderService esOrderService) {
        this.esOrderService = esOrderService;
    }
}
