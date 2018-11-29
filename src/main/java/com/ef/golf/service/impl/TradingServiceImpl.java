package com.ef.golf.service.impl;

import com.ef.golf.mapper.TradingMapper;
import com.ef.golf.pojo.Trading;
import com.ef.golf.service.TradingService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/7 10:39
 */
@Repository
public class TradingServiceImpl implements TradingService{
    @Resource
    private TradingMapper tradingMapper;
    @Override
    public int deleteByPrimaryKey(Integer tradingId) {
        return tradingMapper.deleteByPrimaryKey(tradingId);
    }

    @Override
    public int insert(Trading record) {
        return tradingMapper.insert(record);
    }

    @Override
    public int insertSelective(Trading record) {
        return tradingMapper.insertSelective(record);
    }

    @Override
    public Trading selectByPrimaryKey(Integer tradingId) {
        return tradingMapper.selectByPrimaryKey(tradingId);
    }

    @Override
    public int updateByPrimaryKeySelective(Trading record) {
        return tradingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Trading record) {
        return tradingMapper.updateByPrimaryKey(record);
    }
}
