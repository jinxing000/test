package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.OrderService;
import com.ef.golf.vo.WorkOrderDeatilsVo;
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
public class WorkOrderDetailsAction extends AbstractService {

    @Resource
    private OrderService orderService;

    //@NotNull(message = "订单编号必须")
    private String orderNo;
    //@NotNull(message = "订单类型必须")//1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程
    private String orderType;

    @Override
    public Object doService() throws Exception {
        WorkOrderDeatilsVo workOrderDeatilsVo = orderService.getOrderDetails(orderNo, orderType);
        if("2".equals(workOrderDeatilsVo.getOrderType())||"3".equals(workOrderDeatilsVo.getOrderType())){
            workOrderDeatilsVo.setCourseType(null);
        }
        return workOrderDeatilsVo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
