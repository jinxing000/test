package com.ef.golf.service;

import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.vo.OrderVo;
import com.ef.golf.vo.SiteOrderVo;
import com.ef.golf.vo.WorkOrderDeatilsVo;
import com.pingplusplus.model.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by xzw on 2017/9/23.
 * 订场服务类
 */
public interface OrderService {

    //球场预定场地
    Order saveSiteOrder(Site_order siteOrder,String orderNo,String pingOrderId,double totalPrice,double couponAmount)throws LoginException, SystemException;
    //教练预定
    Order saveCoachOrder(Coach_order coach_order,String ticketId,String orderNo,String pingOrderId,Double amount,double totalPrice,double couponAmount) throws LoginException, SystemException;
    //课程预定
    Order saveCourseOrder(Course_order course_order,String orderNo,String pingOrderId,Double amount,double totalPrice,double couponAmount,String ticketId)throws LoginException, SystemException;
    //愿望订单
    Order saveHopeOrder(Integer userId,String pingOrderNo,String orderId,Double helpMoney) throws LoginException, SystemException;
    //商家订单
    Order saveMerchantOrder(Integer userId,String pingOrderNo,String pingId,Double amount,Double totalPrice,Double couponAmount,String ticketId) throws LoginException, SystemException;
    SiteOrderVo getSiteOrder(Order order) throws LoginException;

    //订单列表
    List<OrderVo> getMyAllOrder(Order order);

    //根据订单编号跟新订单状态
    int updateByOrderNo(/*String orderNo, String status, String tradeNo, String payment_account, String channel,*/ Event event);

    int insertSelective(Order record);

    //根据订单编号(本系统)查询ping++系统的订单编号
    String getPingOrderId(String orderId);
    //根据订单编号(本系统)查询用户账户id
    int getUserAccountId(String orderId);
    Integer selectOrderIdByPingId(String pingId);
    //根据订单编号（本系统）查询订单信息
    Order getOrderByNo(String orderNo);
    int updateByPrimaryKeySelective(Order record);
    /** 工单处理*/
    WorkOrderDeatilsVo getOrderDetails(String orderNo,String orderType);
    int updateWorkOrderStauts(Map map);
    int delWorkOrder(Map map);
    Order selectByPrimaryKey(Integer orderId);
    /** 逻辑删除本地主order */
    int updateOrderIsDel(Integer orderId);
        //确认收货
    int confirmGetOrder(Integer efOrderId, Integer shopOrderId);

    //球童成功预约订单 到达预定时间自动更新状态为待评价
    int updateCaddieOrderState();
    int updateSiteOrderState();
    int chaoshiOrder();

    int findByTickId(String ticketid);

    int siteOrderTuiDin(Integer orderId,String userId) throws SystemException;
}
