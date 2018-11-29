package com.ef.golf.mapper;

import com.ef.golf.pojo.Praise;

public interface PraiseMapper {


    int deleteByPrimaryKey(Integer praiseId);

    int removePraise(Praise record);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer praiseId);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);
}