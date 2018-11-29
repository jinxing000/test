package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsSpec;

public interface GoodsSpecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    GoodsSpec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);
}