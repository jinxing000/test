package com.ef.golf.mapper;

import com.ef.golf.pojo.Recharge;

public interface RechargeMapper {
    int deleteByPrimaryKey(Integer rechargeId);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer rechargeId);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);

    Double getTodayRechargeMoney(Integer userId);

    Integer selectRechargeCountByUserId(Integer userId);

}