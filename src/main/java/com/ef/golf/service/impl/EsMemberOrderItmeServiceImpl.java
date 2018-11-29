package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsMemberOrderItemMapper;
import com.ef.golf.pojo.EsMemberOrderItem;
import com.ef.golf.service.EsMemberOrderItmeService;
import com.ef.golf.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EsMemberOrderItmeServiceImpl implements EsMemberOrderItmeService{
    @Autowired
    EsMemberOrderItemMapper esMemberOrderItemMapper;

    @Override
    public int totalCount(Integer order_id) {
        return 0;
    }

    @Override
    public List<OrderItemVo> getOrderItemList(Map map) {
        return esMemberOrderItemMapper.getOrderItemList(map);
    }

    @Override
    public int insertSelective(EsMemberOrderItem record) {
        return esMemberOrderItemMapper.insertSelective(record);
    }
}
