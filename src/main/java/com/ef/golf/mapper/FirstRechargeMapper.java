package com.ef.golf.mapper;

import com.ef.golf.pojo.FirstRecharge;

import java.util.List;

public interface FirstRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstRecharge record);

    int insertSelective(FirstRecharge record);

    FirstRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FirstRecharge record);

    int updateByPrimaryKey(FirstRecharge record);

    List<FirstRecharge>selectAllFirstRecharge();
}