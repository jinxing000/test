package com.ef.golf.mapper;

import com.ef.golf.pojo.OrderItem;
import com.ef.golf.pojo.OrderItemWithBLOBs;
import com.ef.golf.vo.OrderItemVo;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(OrderItemWithBLOBs record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(OrderItemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderItemWithBLOBs record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> getItemList(Integer order_id);
    List<OrderItemVo> getOrderList(Map map);
    List<OrderItem> itemList(Map map);
    int updateItemStruts(Map map);

}