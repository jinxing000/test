package com.ef.golf.mapper;

import com.ef.golf.pojo.Freeze;

public interface FreezeMapper {
    int deleteByPrimaryKey(Integer freezeId);

    int insert(Freeze record);

    int insertSelective(Freeze record);

    Freeze selectByPrimaryKey(Integer freezeId);

    int updateByPrimaryKeySelective(Freeze record);

    int updateByPrimaryKey(Freeze record);
}