package com.ef.golf.action;


import com.ef.golf.common.OrderStatus;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Order;
import com.ef.golf.service.OrderService;
import com.ef.golf.service.Qiutong_orderService;
import com.ef.golf.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 工单状态更新查询
 * 1.待支付 2.球位确认中 3.确认成功 4.待评价 5.已完成 6.取消支付
 * 7.已退订 8.待回应 9.成功预约 10.已取消 11.未接受
 * 12.待发货 13.已发货  14.申请退货中 15.待寄件 16.退货中 17.已退货
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserOrderStatusAction extends AbstractService {

    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private OrderStatus orderStatus;
    @Resource
    private Qiutong_orderService qiutongOrderService;

    //@NotNull(message = "当前用户ID必须")
    private Integer userId;
    //@NotNull(message = "当前处理工单人uid不可为空!")
    private String uid;
    //@NotNull(message = "用户类型必须!教练，2/球童，3")
    private String userType;
    private String coachWorkType;//教练工单类型 1,陪打 2，教学
    //@NotNull(message = "订单id必须")
    private Integer orderId;
    //@NotNull(message = "订单编号必须")
    private String orderNo;
    private String stauts;//
    private String appointTime;
    private String userPhone;

    @Override
    public Object doService() throws Exception {
        /** 获取orderState */
        Order order = orderService.getOrderByNo(orderNo);
        String type = order.getOrderType();
        String orderState = (String) orderStatus.getOrderStatus().get(type);
        /**==========================================订场订单处理=========================================== */

        return null;
    }


}
