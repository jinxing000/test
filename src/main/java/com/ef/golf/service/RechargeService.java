package com.ef.golf.service;

import com.ef.golf.pojo.Recharge;

import java.util.List;

public interface RechargeService {

    int insertSelective(Recharge recharge,Integer userId);
    Double getTodayRechargeMoney(Integer userId);

    Integer selectRechargeCountByUserId(Integer userId);
}
