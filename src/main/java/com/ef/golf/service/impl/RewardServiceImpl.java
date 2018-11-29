package com.ef.golf.service.impl;

import com.ef.golf.mapper.RewardMapper;
import com.ef.golf.pojo.Reward;
import com.ef.golf.service.RewardService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/15 19:20
 */
@Repository
public class RewardServiceImpl implements RewardService {

    @Resource
    private RewardMapper rewardMapper;

    @Override
    public List<Reward> getAllReward(String userType) {
        return rewardMapper.getAllReward(userType);
    }
}
