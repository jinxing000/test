package com.ef.golf.service;

import com.ef.golf.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface DelOrderService {

    public int updateOrderStruts(Map map);

    public List<OrderItem> getItemList(Integer order_id);

    public  int deleteByPrimaryKey(Integer itemId);

    public int deleteOrder(Integer order_id);

}