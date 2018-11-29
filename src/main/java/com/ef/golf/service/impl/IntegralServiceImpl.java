package com.ef.golf.service.impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.ef.golf.mapper.IntegralMapper;
import com.ef.golf.mapper.IntegralRuleMapper;
import com.ef.golf.pojo.Integral;
import com.ef.golf.pojo.IntegralRule;
import com.ef.golf.service.IntegralService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


@Repository
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralMapper integralMapper;
    @Resource
    private IntegralRuleMapper integralRuleMapper;

    public int insertForRegist(Long userId) {
        Integral integral=new Integral();
        System.out.println(new Date());
        integral.setCreateTime(new Date());
        integral.setIsAlive("1");
        integral.setModifyTime(new Date());
        integral.setScoreTotal(200);
        integral.setUserId(userId);
        return integralMapper.insert(integral);
    }

    @Override
    public Integer getUserTotalScore(Integer userId) {
        return integralMapper.getUserTotalScore(userId);
    }

    @Override
    public Integer updateUserTotalScore(Map map) {
        System.out.println("更新积分");
        return integralMapper.updateUserTotalScore(map);
    }

    @Override
    public IntegralRule selectRuleByType(String type) {
        return integralRuleMapper.selectRuleByType(type);
    }
}
