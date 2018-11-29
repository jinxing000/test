package com.ef.golf.mapper;

import com.ef.golf.pojo.System_pay;

public interface System_payMapper {
    int deleteByPrimaryKey(Integer sysPayId);

    int insert(System_pay record);

    int insertSelective(System_pay record);

    System_pay selectByPrimaryKey(Integer sysPayId);

    int updateByPrimaryKeySelective(System_pay record);

    int updateByPrimaryKey(System_pay record);
}