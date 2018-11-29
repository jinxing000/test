package com.ef.golf.service;

import com.ef.golf.pojo.Reward;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/15 19:19
 */
public interface RewardService {

    List<Reward> getAllReward(String userType);

}
