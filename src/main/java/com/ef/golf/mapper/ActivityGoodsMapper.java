package com.ef.golf.mapper;

import com.ef.golf.pojo.ActivityGoods;

public interface ActivityGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGoods record);

    int insertSelective(ActivityGoods record);

    ActivityGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityGoods record);

    int updateByPrimaryKey(ActivityGoods record);
}