package com.ef.golf.mapper;

import com.ef.golf.pojo.Back;

public interface BackMapper {
    int deleteByPrimaryKey(Integer backId);

    int insert(Back record);

    int insertSelective(Back record);

    Back selectByPrimaryKey(Integer backId);

    int updateByPrimaryKeySelective(Back record);

    int updateByPrimaryKey(Back record);
}