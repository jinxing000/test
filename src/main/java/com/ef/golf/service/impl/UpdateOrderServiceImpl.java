package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsOrderMapper;
import com.ef.golf.service.UpdateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService{
    @Autowired
    EsOrderMapper esOrderMapper;
    @Override
    public int upOrderPayStatus(Map map) {
        return esOrderMapper.upOrderPayStatus(map);
    }
}
