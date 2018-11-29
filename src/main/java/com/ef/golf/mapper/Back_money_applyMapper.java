package com.ef.golf.mapper;

import com.ef.golf.pojo.Back_money_apply;

public interface Back_money_applyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(Back_money_apply record);

    int insertSelective(Back_money_apply record);

    Back_money_apply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(Back_money_apply record);

    int updateByPrimaryKey(Back_money_apply record);
}