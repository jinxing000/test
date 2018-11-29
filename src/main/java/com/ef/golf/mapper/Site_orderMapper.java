package com.ef.golf.mapper;

import com.ef.golf.pojo.Order;
import com.ef.golf.pojo.Site_order;
import com.ef.golf.pojo.User;
import com.ef.golf.vo.OrderVo;
import com.ef.golf.vo.QiuTongOrderListVo;
import com.ef.golf.vo.SiteOrderDatailVo;
import com.ef.golf.vo.SiteOrderListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Site_orderMapper {
    int deleteByPrimaryKey(Integer siteOrderId);

    int insert(Site_order record);

    int insertSelective(Site_order record);

    Site_order selectByPrimaryKey(Integer siteOrderId);

    Site_order getSiteOrderDetailsByOrderId(Integer orderId);

    int updateByPrimaryKeySelective(Site_order record);

    int updateByPrimaryKey(Site_order record);

    SiteOrderDatailVo selectSiteOrderDetail(Integer order_id);

    //订单列表
    List<OrderVo> getMyAllOrderListPage(Order order);
    //个人中心订单列表
    List<SiteOrderListVo>getSiteOrderList(Map map);
    Integer getSiteOrderListCount(Map map);

    SiteOrderListVo getSiteOrderDetails(Integer orderId);
}