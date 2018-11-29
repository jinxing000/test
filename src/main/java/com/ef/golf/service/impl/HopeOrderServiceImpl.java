package com.ef.golf.service.impl;

import com.ef.golf.mapper.HopeOrderMapper;
import com.ef.golf.pojo.HopeOrder;
import com.ef.golf.service.HopeOrderService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/4/24 14:34
 */
@Repository
public class HopeOrderServiceImpl implements HopeOrderService{

    @Resource
    private HopeOrderMapper hopeOrderMapper;

    @Override
    public int deleteByPrimaryKey(Long hopeOrderId) {
        return hopeOrderMapper.deleteByPrimaryKey(hopeOrderId);
    }

    @Override
    public int insert(HopeOrder record) {
        return hopeOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(HopeOrder record) {
        return hopeOrderMapper.insertSelective(record);
    }

    @Override
    public HopeOrder selectByPrimaryKey(Long hopeOrderId) {
        return hopeOrderMapper.selectByPrimaryKey(hopeOrderId);
    }

    @Override
    public int updateByPrimaryKeySelective(HopeOrder record) {
        return hopeOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HopeOrder record) {
        return hopeOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer getHopeIdByOrderId(Integer orderId) {
        return hopeOrderMapper.getHopeIdByOrderId(orderId);
    }
}
