package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsType;
import com.ef.golf.pojo.GoodsTypeWithBLOBs;

public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(GoodsTypeWithBLOBs record);

    int insertSelective(GoodsTypeWithBLOBs record);

    GoodsTypeWithBLOBs selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(GoodsTypeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsTypeWithBLOBs record);

    int updateByPrimaryKey(GoodsType record);
}