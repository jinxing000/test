package com.ef.golf.service.impl;

import com.ef.golf.mapper.JiaoYiHuizongMapper;
import com.ef.golf.mapper.RechargeMapper;
import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.pojo.Recharge;
import com.ef.golf.service.RechargeService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RechargeServiceImpl implements RechargeService{

    @Resource
    private RechargeMapper rechargeMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;
    /**
     * id;
     userId;
     type;
     createTime;

     outIn;
     money;
     1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 发红包 11-赠送
     */
    @Override
    public int insertSelective(Recharge recharge,Integer userId) {
        return rechargeMapper.insertSelective(recharge);
    }

    @Override
    public Double getTodayRechargeMoney(Integer userId) {
        return rechargeMapper.getTodayRechargeMoney(userId);
    }

    @Override
    public Integer selectRechargeCountByUserId(Integer userId) {
        return rechargeMapper.selectRechargeCountByUserId(userId);
    }
}
