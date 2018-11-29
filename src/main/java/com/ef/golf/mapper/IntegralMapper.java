package com.ef.golf.mapper;

import com.ef.golf.pojo.Integral;

import java.util.Map;

public interface IntegralMapper {
    int deleteByPrimaryKey(Integer integralId);

    int insert(Integral record);

    int insertSelective(Integral record);

    Integral selectByPrimaryKey(Integer integralId);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);
    Integer getUserTotalScore(Integer userId);
    Integer updateUserTotalScore(Map map);
}