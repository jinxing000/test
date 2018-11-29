package com.ef.golf.mapper;


import com.ef.golf.pojo.Reward;

import java.util.List;

public interface RewardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reward record);

    int insertSelective(Reward record);

    Reward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reward record);

    int updateByPrimaryKey(Reward record);

    List<Reward> getAllReward(String userType);
}