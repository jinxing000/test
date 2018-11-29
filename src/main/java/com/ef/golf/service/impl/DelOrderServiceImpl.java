package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsOrderMapper;
import com.ef.golf.mapper.OrderItemMapper;
import com.ef.golf.pojo.OrderItem;
import com.ef.golf.service.DelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DelOrderServiceImpl implements DelOrderService{


    @Autowired
    EsOrderMapper esOrderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public int deleteOrder(Integer order_id) {
        return esOrderMapper.deleteByPrimaryKey(order_id);
    }

    @Override
    public List<OrderItem> getItemList(Integer order_id) {
        return orderItemMapper.getItemList(order_id);
    }

    @Override
    public int deleteByPrimaryKey(Integer itemId) {
        return orderItemMapper.deleteByPrimaryKey(itemId);
    }

    @Override
    public int updateOrderStruts(Map map) {
        return esOrderMapper.upOrderStatus(map);
    }


}
