package com.ef.golf.mapper;

import com.ef.golf.pojo.Order_Meta;

public interface Order_MetaMapper {
    int deleteByPrimaryKey(Integer metaid);

    int insert(Order_Meta record);

    int insertSelective(Order_Meta record);

    Order_Meta selectByPrimaryKey(Integer metaid);

    int updateByPrimaryKeySelective(Order_Meta record);

    int updateByPrimaryKeyWithBLOBs(Order_Meta record);

    int updateByPrimaryKey(Order_Meta record);
}