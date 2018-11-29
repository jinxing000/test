package com.ef.golf.service;

import com.ef.golf.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getItemList(Integer order_id);
}
