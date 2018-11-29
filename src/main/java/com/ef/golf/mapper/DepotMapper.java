package com.ef.golf.mapper;

import com.ef.golf.pojo.Depot;

public interface DepotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depot record);

    int insertSelective(Depot record);

    Depot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depot record);

    int updateByPrimaryKey(Depot record);

    int  getCount();
}