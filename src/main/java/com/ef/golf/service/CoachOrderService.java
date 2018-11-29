package com.ef.golf.service;

import com.ef.golf.pojo.Coach_order;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CoachOrderListVo;
import com.ef.golf.vo.CoachWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;

import java.util.Map;

public interface CoachOrderService {
    Coach_order getCoachOrderByOrderId(Integer orderId);

    PageBean<CoachWorkOrderVo>getCoachWorkOrderListPage(Integer userId,String orderState,Integer pageNo,Integer pageSize);

    int updateWorkOrderStauts(CaddieWorkOrderVo coachWorkOrderVo);

    int delWorkOrder(CaddieWorkOrderVo coachWorkOrderVo);

    PageBean getCoachOrderList(String createUser,String orderState,Integer pageNo,Integer pageSize);

    Coach_order getCoachOrderDetails(Integer orderId);

    CoachOrderListVo  getCoachOrderDetailsByOrderId(Integer orderId);//适应手机端直接跳转订单详情
}
