package com.ef.golf.mapper;

import com.ef.golf.pojo.IntegralRule;

public interface IntegralRuleMapper {
    int deleteByPrimaryKey(Integer irid);

    int insert(IntegralRule record);

    int insertSelective(IntegralRule record);

    IntegralRule selectByPrimaryKey(Integer irid);

    int updateByPrimaryKeySelective(IntegralRule record);

    int updateByPrimaryKey(IntegralRule record);

    IntegralRule selectRuleByType(String type);
}