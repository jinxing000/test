package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsDepot;

public interface GoodsDepotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDepot record);

    int insertSelective(GoodsDepot record);

    GoodsDepot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsDepot record);

    int updateByPrimaryKey(GoodsDepot record);
}