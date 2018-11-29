package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.CoachOrderService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.Qiutong_orderService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 教练工单查询
 * 1.待支付 2.球位确认中 3.确认成功 4.待评价 5.已完成 6.取消支付
 * 7.已退订 8.待回应 9.成功预约 10.已取消 11.未接受
 * 12.待发货 13.已发货  14.申请退货中 15.待寄件 16.退货中 17.已退货
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkOrderAction extends AbstractService {

    @Resource
    private CoachOrderService coachOrderService;
    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private CourseService courseService;

    //@NotNull(message = "用户ID必须")
    private Integer userId;
    //@NotNull(message = "用户类型必须!教练，2/球童，3")
    private String userType;
    private String coachType;//1 教练单 2 课程单
    private String orderState; //订单状态 8.待回应 9.成功预约 5.已完成 4.待评价 10.已取消 11.未接受
    private int pageNo = 1;
    private int pageSize = 5;

    @Override
    public Object doService() throws Exception {
        try {
            if (coachType != null) {
                /** 教练订单状态 15.待回应 16.成功预约 18.已完成 17.待评价 20.已取消 19.未接受 */
                if (userType.equals("2") && coachType.equals("1")) {
                    PageBean pageBean = coachOrderService.getCoachWorkOrderListPage(userId, orderState, pageNo, pageSize);
                    return pageBean;
                }
                /** 课程订单状态 22.待回应 23.成功预约 25.已完成 24.待评价 27.已取消 26.未接受 */
                if (userType.equals("2") && coachType.equals("2")) {
                    PageBean pageBean = courseService.courseWorkOrderList(userId, orderState, pageNo, pageSize);
                    return pageBean;
                }
            }

            /** 球童订单状态 8.待回应 9.成功预约 11.已完成 10.待评价 13.已取消 12.未接受 */
            if (userType.equals("3")) {
                PageBean pageBean = qiutongOrderService.getQiuTongWorkOrderListPage(userId, orderState, pageNo, pageSize);
                return pageBean;
            }
            /** 商城订单状态 全部订单 28.待支付 29.待发货 30.已发货 31.待评价 */
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(Constant.ERR_UNKNOW);
        }

        return null;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }
}
