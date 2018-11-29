package com.ef.golf.mapper;

import com.ef.golf.pojo.EsOrder;
import com.ef.golf.pojo.EsOrderWithBLOBs;
import com.ef.golf.vo.*;

import java.util.List;
import java.util.Map;

public interface EsOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(EsOrder record);

    int insertSelective(EsOrder record);

    EsOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(EsOrder record);

    //int updateByPrimaryKeyWithBLOBs(EsOrder record);

    int updateByPrimaryKey(EsOrder record);

    //List<CartItem> selectListGoods(Integer userId);

    int orderCount(String sn);

    int updateItemsJson(Map map);

    List<OrderItemVo> orderList(Map map);

    List<EsOrderVo> userOrderList(Map map);

    int totalCount(Map map);

    int orderCounts(Map map);

    int updateOrderStatus(Map map);

    int upOrderStatus(Map map);

    int upOrderPayStatus(Map map);

    SpeItemVo selectSpeItemVo(Integer goods_id);
    //根据主订单的order_no查询对应的商城订单的order_id
    Integer getShopOrderIdByMainOrderNo(String orderNo);
    //根据es_goods表orderId 查询赠券信息
    List<ShopSendTicketVo>getShopOrderSendTicket(String orderId);

    EsOrderVo getEsOrderBySn(String orderNo);
}