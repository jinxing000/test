package com.ef.golf.mapper;

import com.ef.golf.pojo.EsMemberOrderItem;
import com.ef.golf.vo.OrderItemVo;

import java.util.List;
import java.util.Map;

public interface EsMemberOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EsMemberOrderItem record);

    int insertSelective(EsMemberOrderItem record);

    EsMemberOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EsMemberOrderItem record);

    int updateByPrimaryKey(EsMemberOrderItem record);

    List<OrderItemVo>getOrderItemList(Map map);

    int totalCount(Integer order_id);


}