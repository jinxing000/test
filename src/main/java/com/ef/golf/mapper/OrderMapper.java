package com.ef.golf.mapper;

import com.ef.golf.pojo.Order;
import com.ef.golf.vo.SiteOrderVo;
import com.ef.golf.vo.WorkOrderDeatilsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    SiteOrderVo getSiteOrder(Order order);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //根据订单编号跟新订单状态
    int updateByOrderNo(@Param("orderNo")String orderNo,@Param("status")String status,@Param("channel")String channel);
    //根据订单id查询账户id
    int getUserAccountId(String orderId);
    //根据订单编号(本系统)查询ping++系统的订单编号
    String getPingOrderId(String orderId);
    Integer selectOrderIdByPingId(String pingId);
    //根据订单编号（本系统）查询订单信息
    Order getOrderByNo(String orderNo);
    /** 工单处理*/
    WorkOrderDeatilsVo getOrderDetails(Map map);
    int updateWorkOrderStauts(Map map);
    int delWorkOrder(Map map);

    /** 逻辑删除本地主order */
    int updateOrderIsDel(Integer orderId);

    int updateCaddieOrderState();
    int updateSiteOrderState();
    List<Order> chaoshiOrder();

    int findByTickId(String ticketid);
}