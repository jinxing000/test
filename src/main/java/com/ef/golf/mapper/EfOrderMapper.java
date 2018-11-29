package com.ef.golf.mapper;

import com.ef.golf.pojo.EfOrder;

public interface EfOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(EfOrder record);

    int insertSelective(EfOrder record);

    EfOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(EfOrder record);

    int updateByPrimaryKey(EfOrder record);
}