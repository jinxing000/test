package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsGllery;

import java.util.List;

public interface GoodsGlleryMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(GoodsGllery record);

    int insertSelective(GoodsGllery record);

    GoodsGllery selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(GoodsGllery record);

    int updateByPrimaryKey(GoodsGllery record);

    List<GoodsGllery>getList(Integer goods_id);
}