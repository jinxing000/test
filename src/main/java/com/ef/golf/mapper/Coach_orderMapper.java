package com.ef.golf.mapper;

import com.ef.golf.pojo.Coach_order;
import com.ef.golf.vo.CoachOrderListVo;
import com.ef.golf.vo.CoachWorkOrderVo;
import com.ef.golf.vo.CaddieWorkOrderVo;

import java.util.List;
import java.util.Map;

public interface Coach_orderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coach_order record);

    int insertSelective(Coach_order record);

    Coach_order selectByPrimaryKey(Integer id);
    Coach_order getCoachOrderByOrderId(Integer orderId);
    int updateByPrimaryKeySelective(Coach_order record);

    int updateByPrimaryKey(Coach_order record);

    Integer coachWorkOrderCount(Map map);

    List<CoachWorkOrderVo> getCoachWorkOrderList(Map map);

    List<CoachOrderListVo>getCoachOrderList(Map map);
    Integer getCoachOrderListCount(Map map);

    Coach_order getCoachOrderDetails(Integer orderId);

    CoachOrderListVo getCoachOrderDetailsByOrderId(Integer orderId);
}