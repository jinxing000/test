package com.ef.golf.service.impl;

import com.ef.golf.mapper.OrderItemMapper;
import com.ef.golf.pojo.OrderItem;
import com.ef.golf.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> getItemList(Integer order_id) {
        return orderItemMapper.getItemList(order_id);
    }
}
