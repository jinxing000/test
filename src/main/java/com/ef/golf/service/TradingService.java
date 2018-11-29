package com.ef.golf.service;

import com.ef.golf.pojo.Trading;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/5/7 10:40
 */
public interface TradingService {
    int deleteByPrimaryKey(Integer tradingId);

    int insert(Trading record);

    int insertSelective(Trading record);

    Trading selectByPrimaryKey(Integer tradingId);

    int updateByPrimaryKeySelective(Trading record);

    int updateByPrimaryKey(Trading record);
}
