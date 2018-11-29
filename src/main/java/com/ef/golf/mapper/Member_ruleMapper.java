package com.ef.golf.mapper;

import com.ef.golf.pojo.Member_rule;

public interface Member_ruleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(Member_rule record);

    int insertSelective(Member_rule record);

    Member_rule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(Member_rule record);

    int updateByPrimaryKey(Member_rule record);
}