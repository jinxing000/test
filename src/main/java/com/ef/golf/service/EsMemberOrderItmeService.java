package com.ef.golf.service;

import com.ef.golf.pojo.EsMemberOrderItem;
import com.ef.golf.vo.OrderItemVo;

import java.util.List;
import java.util.Map;

public interface EsMemberOrderItmeService {

    int insertSelective(EsMemberOrderItem record);
    List<OrderItemVo> getOrderItemList(Map map);
    int totalCount(Integer order_id);
}
